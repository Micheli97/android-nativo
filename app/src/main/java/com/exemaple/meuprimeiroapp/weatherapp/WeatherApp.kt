package com.example.meuprimeiroapp.weatherapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// ==============================================================================
// CAMADA DE DADOS (MODEL)
// ==============================================================================

/**
 * Padrão 'State Pattern': Define todos os estados possíveis da interface.
 * Usar 'sealed interface' obriga o compilador a garantir que tratamos
 * todos os casos (Loading, Success, Error) no 'when'.
 * @param T O tipo de dado que será retornado no sucesso (ex: Weather).
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>          // Representa o spinner girando
    data class Success<T>(val data: T) : UiState<T> // Dados carregados com sucesso
    data class Error(val message: String) : UiState<Nothing> // Ocorreu uma falha
}

/** Modelo simples representando os dados do clima */
data class Weather(val city: String, val temperature: Int, val condition: String)

/**
 * Repositório responsável por buscar os dados (fonte da verdade).
 * @Singleton: Garante que o Hilt crie apenas UMA instância dessa classe para o app todo.
 * @Inject: Avisa ao Hilt como criar essa classe.
 */
@Singleton
class WeatherRepository @Inject constructor() {
    // 'suspend' indica que essa função pode pausar a execução sem travar a tela (Corrotina)
    suspend fun getWeather(city: String): Weather {
        delay(1000) // Simula o atraso de uma requisição de rede real (1 segundo)
        return Weather(city, 25, "Sunny") // Retorna dados "fake" para teste
    }
}

// ==============================================================================
// CAMADA DE LÓGICA (VIEWMODEL)
// ==============================================================================

/**
 * O ViewModel gerencia o estado da UI e sobrevive a mudanças de configuração (rotação de tela).
 * @HiltViewModel: Permite que o Hilt injete dependências automaticamente aqui.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository, // Hilt injeta o repositório pronto
    private val savedStateHandle: SavedStateHandle // Guarda dados mesmo se o sistema matar o app para liberar memória
) : ViewModel() {

    // --- Gerenciamento de Estado (Backing Property) ---
    // _uiState é privado e mutável (só o ViewModel pode alterar)
    private val _uiState = mutableStateOf<UiState<Weather>>(UiState.Loading)
    // uiState é público e somente leitura (a tela só pode ler, nunca alterar)
    val uiState: State<UiState<Weather>> = _uiState

    // --- Gerenciamento da Cidade com Persistência ---
    // Tenta recuperar a cidade salva anteriormente pelo sistema. Se não houver, usa "New York".
    private val _city = mutableStateOf(savedStateHandle.get<String>("city") ?: "New York")
    val city: State<String> = _city

    /**
     * Atualiza a cidade e salva no SavedStateHandle para persistência.
     */
    fun updateCity(newCity: String) {
        _city.value = newCity
        savedStateHandle["city"] = newCity // Salva o valor para sobreviver à morte do processo
        loadWeather() // Recarrega o clima automaticamente ao digitar
    }

    /**
     * Função assíncrona para buscar o clima.
     * Usa viewModelScope para lançar a corrotina (cancela automaticamente se o ViewModel morrer).
     */
    fun loadWeather() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // 1. Muda estado para Carregando
            try {
                // 2. Chama o repositório (demora 1s) e atualiza para Sucesso
                val result = repository.getWeather(_city.value)
                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                // 3. Se der erro, atualiza para Erro
                _uiState.value = UiState.Error(e.message ?: "Failed to load weather")
            }
        }
    }
}

// ==============================================================================
// CAMADA DE UI (VIEW / COMPOSE)
// ==============================================================================

/**
 * Tela principal.
 * @param viewModel: O Hilt injeta o ViewModel automaticamente com 'hiltViewModel()'.
 */
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    // 'by' é um delegate: ele extrai o valor de .value automaticamente e
    // assina esse Composable para recompor sempre que o valor mudar.
    val uiState by viewModel.uiState
    val city by viewModel.city

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Campo de texto onde o usuário digita
        TextField(
            value = city,
            onValueChange = { novoTexto ->
                // Evento sobe para o ViewModel (Unidirectional Data Flow)
                viewModel.updateCity(novoTexto)
            },
            label = { Text("City") }
        )

        // Bloco de decisão: O que mostrar na tela baseado no estado atual?
        when (val state = uiState) {
            is UiState.Loading -> {
                // Mostra texto de carregamento (poderia ser um CircularProgressIndicator)
                Text("Loading...")
            }
            is UiState.Success -> {
                // Smart Cast: O Kotlin sabe que 'state' é do tipo Success, então podemos acessar '.data'
                val weather = state.data
                Text("City: ${weather.city}")
                Text("Temperature: ${weather.temperature}°C")
                Text("Condition: ${weather.condition}")
            }
            is UiState.Error -> {
                // Mostra mensagem de erro em vermelho (sugestão visual)
                Text("Error: ${state.message}", color = androidx.compose.ui.graphics.Color.Red)
            }
        }
    }
}