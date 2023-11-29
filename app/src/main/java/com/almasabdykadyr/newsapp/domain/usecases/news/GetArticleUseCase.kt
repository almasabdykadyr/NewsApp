package com.almasabdykadyr.newsapp.domain.usecases.news

import com.almasabdykadyr.newsapp.data.local.NewsDao
import com.almasabdykadyr.newsapp.domain.model.Article

class GetArticleUseCase(private val newsDao: NewsDao) {

    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticle(url)
    }
}