package com.almasabdykadyr.newsapp.data.remote.dto

import com.almasabdykadyr.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)