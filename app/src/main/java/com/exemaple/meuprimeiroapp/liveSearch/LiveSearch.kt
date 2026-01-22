package com.exemaple.meuprimeiroapp.liveSearch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

// 1. A INTERFACE QUE FALTAVA
// Ela define o "contrato": todo repositório de busca TEM que ter essa função.
interface SearchRepository {
    suspend fun search(query: String): List<String>
}

// 2. A IMPLEMENTAÇÃO FALSA (MOCK)
// Note os dois pontos ": SearchRepository". Isso diz que ela cumpre o contrato.
class FakeSearchRepository : SearchRepository {
    private val items = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig")

    // A palavra 'override' confirma que estamos implementando a função da interface
    override suspend fun search(query: String): List<String> {
        // Simula um delay de rede se quiser testar o loading
        // delay(1000)
        if (query.isBlank()) return emptyList()
        return items.filter { it.contains(query, ignoreCase = true) }
    }
}
@OptIn(FlowPreview::class)
@Composable
fun  LiveSearch(repository: SearchRepository = FakeSearchRepository()){
    var query by rememberSaveable { mutableStateOf("") }
    val results by produceState<List<String>>(initialValue = emptyList(), query) {
        // 1. O ESCOPO: O produceState abre uma corrotina que fica viva.

        // 2. A CONVERSÃO (Input): O snapshotFlow fica "olhando" a variável 'query'.
        // Ele avisa o Flow sempre que ela muda, SEM matar a corrotina.
        snapshotFlow { query }

            // 3. A LÓGICA (Middle): Aplicamos o debounce no fluxo.
            .debounce(300L)

            // 4. A ATUALIZAÇÃO (Output): Quando o debounce deixa passar...
            .collect { q ->
                // ...buscamos na API e atualizamos o valor do produceState.
                value = repository.search(q)
            }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            items(results){ result ->
                Text(
                    text = result,
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                )

            }
        }
    }

}

