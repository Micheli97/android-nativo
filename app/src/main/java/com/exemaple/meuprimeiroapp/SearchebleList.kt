package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val LocalSearchQuery = compositionLocalOf { mutableStateOf("") }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchebleList(items: List<String>) {
    val queryState = remember { mutableStateOf("") }
    val filterdItens = remember(queryState.value) {
        items.filter { it.contains(queryState.value, ignoreCase = true) }
    }

    CompositionLocalProvider(LocalSearchQuery provides queryState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(filterdItens) { item ->
                    Text(
                        item, modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )

                }
            }
        }
    }

}

@Composable
fun SearchBar() {
    val queryState = LocalSearchQuery.current
    TextField(
        value = queryState.value,
        onValueChange = { queryState.value = it },
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun SearchebleListPreview() {
    SearchebleList(items = listOf("Apple", "Banana", "Cherry", "Date"))
}