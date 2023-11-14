package com.almasabdykadyr.newsapp.domain.usecases.appentry

import com.almasabdykadyr.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}