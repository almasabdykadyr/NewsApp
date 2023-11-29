package com.almasabdykadyr.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getArticlesUseCase: GetArticlesUseCase,
    val getArticleUseCase: GetArticleUseCase,
)