package com.almasabdykadyr.newsapp.domain.usecases.news

import com.almasabdykadyr.newsapp.data.local.NewsDao
import com.almasabdykadyr.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}