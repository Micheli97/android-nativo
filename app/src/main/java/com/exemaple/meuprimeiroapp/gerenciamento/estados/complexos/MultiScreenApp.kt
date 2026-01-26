package com.exemaple.meuprimeiroapp.gerenciamento.estados.complexos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State // Importação correta
import androidx.compose.runtime.getValue // Necessário para o 'by'
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost // Função, não a Interface
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {
    private val _selectedItem = mutableStateOf<String?>(null)
    // Usamos a interface State do Compose
    val selectItem: State<String?> get() = _selectedItem

    fun selectItem(itemId: String) {
        _selectedItem.value = itemId
    }
}



@Composable
fun MultiScreenApp() {
    // 1. O Controle: Igual ao ScrollController ou TextEditingController do Flutter.
    // Ele lembra onde você está e gerencia a "pilha" de telas.
    val navController = rememberNavController()


    // 2. O NavHost: É o "Container" que vai trocar as telas.
    // 'startDestination' é a sua "home", a primeira tela a aparecer.
    NavHost(navController = navController, startDestination = "home") {


        // 3. Definindo as Rotas:
        // No Flutter você criaria uma classe 'TelaHome()'.
        // Aqui, você associa um nome ("home") a uma função Composable.
        composable("home") {
            // hiltViewModel() sem argumentos cria um ViewModel no escopo desta rota
            HomeScreen(navController, hiltViewModel())
        }


        composable("detail/{itemId}") { backStackEntry ->

            // 1. Aqui você avisa ao Android: "Essa tela aceita um parâmetro chamado itemId".
            // O 'backStackEntry' é como se fosse o "envelope" que chegou pelo correio.

            val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
            // 2. Você abre o envelope e lê o valor que foi passado na URL.

            // Para compartilhar o ViewModel, usamos o escopo da rota "home"
            // 3. Você olha para trás na pilha de telas e pega a referência da "home".
            // Isso serve para manter o ViewModel vivo enquanto você estiver nesse fluxo.
            val parentEntry = remember(backStackEntry) {
                // Para navegar, basta chamar o método navigate
                navController.getBackStackEntry("home")
            }

            // 3. Você olha para trás na pilha de telas e pega a referência da "home".
            // Isso serve para manter o ViewModel vivo enquanto você estiver nesse fluxo.

            DetailsScreen(
                itemId = itemId,
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, viewModel: AppViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Home", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = {
            viewModel.selectItem("1")
            navController.navigate("detail/1")
        }) {
            Text("View Item 1")
        }
    }
}

@Composable
fun DetailsScreen(itemId: String, navController: NavController, viewModel: AppViewModel) {
    // Uso do 'by' requer import do runtime.getValue
    val selectedItem by viewModel.selectItem

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Details for Item $itemId", style = MaterialTheme.typography.headlineMedium)
        Text("Selected in ViewModel: ${selectedItem ?: "None"}")
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}