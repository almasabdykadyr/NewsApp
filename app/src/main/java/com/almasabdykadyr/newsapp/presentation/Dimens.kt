package com.almasabdykadyr.newsapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PageSize
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

object Dimens {

    val MediumPadding1 = 24.dp
    val MediumPadding2 = 32.dp

    val IndicatorSize = 14.dp
    val PageIndicatorWidth = 52.dp

    @ExperimentalFoundationApi
    val OnBoardingScreenPagerSize = object : PageSize {
        override fun Density.calculateMainAxisPageSize(availableSpace: Int, pageSpacing: Int): Int {
            return (availableSpace)
        }
    }
}