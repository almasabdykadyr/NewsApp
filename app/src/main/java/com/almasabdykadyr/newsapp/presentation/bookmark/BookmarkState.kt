package com.almasabdykadyr.newsapp.presentation.bookmark

import com.almasabdykadyr.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)