package com.almasabdykadyr.newsapp.di

import android.app.Application
import com.almasabdykadyr.newsapp.data.manager.LocalUserManagerImpl
import com.almasabdykadyr.newsapp.data.remote.NewsApi
import com.almasabdykadyr.newsapp.data.repository.NewsRepositoryImpl
import com.almasabdykadyr.newsapp.domain.manager.LocalUserManager
import com.almasabdykadyr.newsapp.domain.repository.NewsRepository
import com.almasabdykadyr.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.almasabdykadyr.newsapp.domain.usecases.appentry.ReadAppEntry
import com.almasabdykadyr.newsapp.domain.usecases.appentry.SaveAppEntry
import com.almasabdykadyr.newsapp.domain.usecases.news.GetNewsUseCase
import com.almasabdykadyr.newsapp.domain.usecases.news.NewsUseCases
import com.almasabdykadyr.newsapp.util.Constants.BASE_URL
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
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository)
    )
}