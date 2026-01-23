package com.exemaple.meuprimeiroapp.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val news: StateFlow<List<NewsItem>> = repository.newsFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun refresh(){
        viewModelScope.launch {
            repository.fetchNews()
        }
    }
}

