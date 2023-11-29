package com.almasabdykadyr.newsapp.domain.usecases.news

import com.almasabdykadyr.newsapp.data.local.NewsDao
import com.almasabdykadyr.newsapp.domain.model.Article

class UpsertArticleUseCase(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}