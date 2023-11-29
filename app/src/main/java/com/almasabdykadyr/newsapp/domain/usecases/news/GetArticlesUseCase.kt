package com.almasabdykadyr.newsapp.domain.usecases.news

import com.almasabdykadyr.newsapp.data.local.NewsDao

class GetArticlesUseCase(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke() {
        newsDao.getArticles()
    }
}