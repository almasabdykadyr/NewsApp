package com.almasabdykadyr.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.almasabdykadyr.newsapp.R

data class Page(
    val title: String, val description: String, @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Test",
        description = "Some description to test page",
        image = R.drawable.onboarding1
    ), Page(
        title = "Test",
        description = "Some description to test page",
        image = R.drawable.onboarding2
    ), Page(
        title = "Test",
        description = "Some description to test page",
        image = R.drawable.onboarding3
    )
)
