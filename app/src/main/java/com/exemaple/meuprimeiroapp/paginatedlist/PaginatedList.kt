package com.exemaple.meuprimeiroapp.paginatedlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaginatedViewModel: ViewModel(){
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> get() = _items
    private var page = 0

    fun loadNextPage(){
        viewModelScope.launch {
            // Simulate network call
            delay(1000)
            _items.value += List(10){"Item ${page * 10 + it}"}
            page++
        }
    }
}


@Composable
fun PaginatedList(viewModel: PaginatedViewModel = viewModel()){
    val items by viewModel.items.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items){item ->
            Text(
                item,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item{
            Button({viewModel.loadNextPage()},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                Text("Load More")
            }
        }
    }

}


