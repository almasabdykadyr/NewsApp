package com.almasabdykadyr.newsapp.presentation.details

sealed class DetailsEvent {

    object UpsertDeleteArticle : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}