package com.exemaple.meuprimeiroapp.offline

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository@Inject constructor(private val newsDao: NewsDao) {
    val newsFlow: Flow<List<NewsItem>> = newsDao.getAll()

    suspend fun fetchNews(){

        val news = listOf(
            NewsItem("1", "Title 1", "Description 1"),
            NewsItem("2", "Title 2", "Description 2"),
            )
        newsDao.insertAll(news)
    }
}