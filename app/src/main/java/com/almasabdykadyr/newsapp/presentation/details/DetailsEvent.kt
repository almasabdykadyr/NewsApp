package com.almasabdykadyr.newsapp.presentation.details

import com.almasabdykadyr.newsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}