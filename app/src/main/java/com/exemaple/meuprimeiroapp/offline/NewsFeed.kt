package com.exemaple.meuprimeiroapp.offline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import java.nio.file.WatchEvent

@Composable
fun NewsFeedScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val news by viewModel.news.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Button({viewModel.refresh()}, modifier = Modifier.fillMaxWidth()) {
            Text("Refresh")
        }

        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            items(news) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(item.title, style = MaterialTheme.typography.titleMedium)
                        Text(item.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NewsFeedScreenPreview() {
    NewsFeedScreen()
}