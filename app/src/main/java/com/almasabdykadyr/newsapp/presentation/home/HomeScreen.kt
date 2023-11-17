package com.almasabdykadyr.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.almasabdykadyr.newsapp.R
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.presentation.Dimens.MediumPadding1
import com.almasabdykadyr.newsapp.presentation.common.ArticlesList
import com.almasabdykadyr.newsapp.presentation.common.SearchBar
import com.almasabdykadyr.newsapp.presentation.navgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigation: (String) -> Unit) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(0..9).joinToString("|") { item -> item.title}
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(text = "", readOnly = true, onValueChanged = {}, onClick = {
            navigation(Route.SearchScreen.route)
        }, onSearch = {})

        Spacer(modifier = Modifier.height(MediumPadding1))

        //TODO: move to new component
        //FIXME: fix paddings
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder),
            maxLines = 1
        )

        Spacer (modifier = Modifier.height(MediumPadding1))

        ArticlesList(articles = articles, modifier = Modifier.padding(top = MediumPadding1), onClick = {
            navigation(Route.DetailsScreen.route)
        })
    }
}

