package com.almasabdykadyr.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticleUseCase.invoke(event.article.url)

                    if (article == null) {
                        upsertArticle(event.article)

                    } else {
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private fun upsertArticle(article: Article) {

        viewModelScope.launch { newsUseCases.upsertArticleUseCase.invoke(article) }
        sideEffect = "Article Saved"
    }


    private fun deleteArticle(article: Article) {

        viewModelScope.launch { newsUseCases.deleteArticleUseCase.invoke(article) }
        sideEffect = "Article Deleted"
    }
}