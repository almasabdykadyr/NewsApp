package com.almasabdykadyr.newsapp.domain.usecases.appentry

import com.almasabdykadyr.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}