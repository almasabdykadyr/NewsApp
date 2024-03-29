package com.almasabdykadyr.newsapp.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.almasabdykadyr.newsapp.R
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.presentation.bookmark.BookmarkScreen
import com.almasabdykadyr.newsapp.presentation.bookmark.BookmarkViewModel
import com.almasabdykadyr.newsapp.presentation.details.DetailsScreen
import com.almasabdykadyr.newsapp.presentation.details.DetailsViewModel
import com.almasabdykadyr.newsapp.presentation.home.HomeScreen
import com.almasabdykadyr.newsapp.presentation.home.HomeViewModel
import com.almasabdykadyr.newsapp.presentation.navgraph.Route
import com.almasabdykadyr.newsapp.presentation.navigator.components.NewsBottomNavigation
import com.almasabdykadyr.newsapp.presentation.navigator.components.NewsBottomNavigationItem
import com.almasabdykadyr.newsapp.presentation.search.SearchScreen
import com.almasabdykadyr.newsapp.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            NewsBottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            NewsBottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            NewsBottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NewsBottomNavigation(
            items = bottomNavigationItems,
            selected = selectedItem,
            onItemClicked = { index ->
                when (index) {
                    0 -> navigateToTap(
                        navController = navController, route = Route.HomeScreen.route
                    )

                    1 -> navigateToTap(
                        navController = navController, route = Route.SearchScreen.route
                    )

                    2 -> navigateToTap(
                        navController = navController, route = Route.BookmarkScreen.route
                    )
                }
            })
    }) {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigateToSearch = {
                    navigateToTap(navController = navController, route = Route.SearchScreen.route)
                }, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                })
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent, navigateToDetails = {
                    navigateToDetails(
                        navController = navController, article = it
                    )
                })
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                //TODO: handle side effect
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->

                        DetailsScreen(article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                })
            }
        }
    }
}

private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }

            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}