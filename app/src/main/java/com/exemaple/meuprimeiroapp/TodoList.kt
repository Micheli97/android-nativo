package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TodoItem(val id: Int, val task: String)

@Composable
private fun TodoList(
    items: List<TodoItem>,
    onAddItem: (String) -> Unit,
    onRemoveItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    var newTask by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            TextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("New task") },
                modifier = Modifier.weight(1f)
            )
            Button({
                if (newTask.isNotBlank()) {
                    onAddItem(newTask)
                    newTask = ""
                }
            }) {
                Text("Add")
            }
        }
        LazyColumn() {
            items(items, key = {it.id}){item ->
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                    ) {
                    Text(item.task)
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Task",
                        modifier = Modifier.size(24.dp)
                            .clickable{onRemoveItem(item.id)}
                    )
                }

            }
        }
    }

}

@Composable
fun TodoScreen() {
    // 1. O ESTADO DA LISTA
    // Cria uma lista vazia inicial. O `remember` garante que a lista nao suma
    // quando a tela for redesenhada.
    var items by remember { mutableStateOf(listOf<TodoItem>()) }

    // 2. O CONTADOR DE ID
    // Um simples inteiro para garantir que cada tarefa tenha um ID único.
    var nextId by remember { mutableStateOf(0) }

    TodoList(
        items = items,
        onAddItem = { task -> "Esta é uma função que RECEBE uma variável que decidi chamar de task -> e então executa o código a seguir usando ela."
            items = items + TodoItem(nextId++, task)
            /*
            O Operador + : Em Koltin, somar um item a uma lista (list + item) não modifica a
            lista original. Ele cria uma nova lista com o item adicionado. Isso é uma característica
            da imutabilidade do Kotlin.

            Por que fazer assim?  O Compose observa a variável items. Se você apenas fizesse item.add(),
            a referência de memória da lista seria a mesma, e o Compose não saberia que precisa atualizar
            a tela. Ao criar uma nova lista e atributir items - ..., o Compose percebe a mudança e
            redesenha a TodoList.

            O nextId: Você usa o ID atual e incrementa para o próximo
            * */
        },
        onRemoveItem = { id ->
            items = items.filter { it.id != id }
            // Filtra a lista mantendo apenas quem tem o ID DIFERENTE do que queremos apagar
        }
    )



}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    TodoList(
        items = listOf(TodoItem(1, "Task 1"), TodoItem(2, "Task 2")),
        onAddItem = {},
        onRemoveItem = {})
}

/* Em dart
* // O parâmetro fica entre parênteses antes do bloco
onAddItem: (String task) {
    setState(() {
        items.add(TodoItem(task));
    });
}
*
*
*
* Em Kotlin
* // O parâmetro fica DENTRO das chaves, separado por uma seta
onAddItem = { task ->
    items = items + TodoItem(nextId++, task)
}
* */