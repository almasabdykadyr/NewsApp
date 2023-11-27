package com.almasabdykadyr.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}