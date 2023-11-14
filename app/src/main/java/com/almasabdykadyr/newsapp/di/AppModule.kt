package com.almasabdykadyr.newsapp.di

import android.app.Application
import com.almasabdykadyr.newsapp.data.manager.LocalUserManagerImpl
import com.almasabdykadyr.newsapp.domain.manager.LocalUserManager
import com.almasabdykadyr.newsapp.domain.usecases.appentry.AppEntryUseCases
import com.almasabdykadyr.newsapp.domain.usecases.appentry.ReadAppEntry
import com.almasabdykadyr.newsapp.domain.usecases.appentry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}