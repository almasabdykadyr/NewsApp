package com.almasabdykadyr.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.almasabdykadyr.newsapp.R
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.domain.model.Source
import com.almasabdykadyr.newsapp.presentation.Dimens.ArticleImageHeight
import com.almasabdykadyr.newsapp.presentation.Dimens.MediumPadding1
import com.almasabdykadyr.newsapp.presentation.details.components.DetailsTopBar
import com.almasabdykadyr.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article, event: (DetailsEvent) -> Unit, navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(onBrowsingClick = {

            Intent(Intent.ACTION_VIEW).also { intent ->

                intent.data = Uri.parse(article.url)
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            }
        }, onShareClick = {
            Intent(Intent.ACTION_SEND).also { intent ->

                intent.putExtra(Intent.EXTRA_TEXT, article.url)
                intent.type = "text/plain"
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            }

        }, onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) }, onBackClick = { navigateUp })

        LazyColumn(
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(
                start = MediumPadding1, end = MediumPadding1, top = MediumPadding1
            )
        ) {
            item {

                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    NewsAppTheme {
        DetailsScreen(article = Article(
            source = Source("engadget", "Engadget"),
            author = "Daniel Cooper",
            title = "The Morning After: Is the M3 iMac worth it?",
            description = "Unlike many of my peers, I prefer desktops to laptops, so I’m always excited when a new iMac rolls off the production line. I’ve had my eye on one for a while, especially now it’s packing an M3 chip\r\n with all the power that promises. Sadly for me and other d…",
            url = "https://www.engadget.com/the-morning-after-is-the-m3-imac-worth-it-121522249.html",
            urlToImage = "https://s.yimg.com/ny/api/res/1.2/rr2uCHkNnpizYD5xsx5alA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2023-11/8f905dc0-7f48-11ee-bf3b-a1d7773a597c",
            publishedAt = "2023-11-13T12:15:22Z",
            content = "Unlike many of my peers, I prefer desktops to laptops, so Im always excited when a new iMac rolls off the production line. Ive had my eye on one for a while, especially now its packing anM3 chip\r\n wi… [+3194 chars]"
        ), event = {}, navigateUp = {})
    }
}