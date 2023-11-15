package com.almasabdykadyr.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.almasabdykadyr.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    val news = newsUseCases.getNewsUseCase.invoke(
        sources = listOf("bbc-news", "abc-news", "bloomberg", "espn")
    ).cachedIn(viewModelScope)
}