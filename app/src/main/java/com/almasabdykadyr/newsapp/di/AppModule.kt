package com.almasabdykadyr.newsapp.di

import android.app.Application
import androidx.room.Room
import com.almasabdykadyr.newsapp.data.local.NewsDao
import com.almasabdykadyr.newsapp.data.local.NewsDatabase
import com.almasabdykadyr.newsapp.data.local.NewsTypeConverter
import com.almasabdykadyr.newsapp.data.manager.LocalUserManagerImpl
import com.almasabdykadyr.newsapp.data.remote.NewsApi
import com.almasabdykadyr.newsapp.data.repository.NewsRepositoryImpl
import com.almasabdykadyr.newsapp.domain.manager.LocalUserManager
import com.almasabdykadyr.newsapp.domain.repository.NewsRepository
import com.almasabdykadyr.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.almasabdykadyr.newsapp.domain.usecases.appentry.ReadAppEntry
import com.almasabdykadyr.newsapp.domain.usecases.appentry.SaveAppEntry
import com.almasabdykadyr.newsapp.domain.usecases.news.DeleteArticleUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.GetArticleUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.GetArticlesUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.GetNewsUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.NewsUseCases
import com.almasabdykadyr.newsapp.domain.usecases.news.SearchNewsUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.UpsertArticleUseCase
import com.almasabdykadyr.newsapp.util.Constants.BASE_URL
import com.almasabdykadyr.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager), saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository, newsDao: NewsDao) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository),
        searchNewsUseCase = SearchNewsUseCase(newsRepository),
        upsertArticleUseCase = UpsertArticleUseCase(newsDao),
        deleteArticleUseCase = DeleteArticleUseCase(newsDao),
        getArticlesUseCase = GetArticlesUseCase(newsDao),
        getArticleUseCase = GetArticleUseCase(newsDao)
    )

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ): NewsDatabase = Room.databaseBuilder(
        context = application.applicationContext,
        klass = NewsDatabase::class.java,
        name = NEWS_DATABASE_NAME
    ).addTypeConverter(NewsTypeConverter()).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}