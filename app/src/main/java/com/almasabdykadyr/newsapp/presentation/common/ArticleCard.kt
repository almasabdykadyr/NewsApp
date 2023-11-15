package com.almasabdykadyr.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.almasabdykadyr.newsapp.R
import com.almasabdykadyr.newsapp.domain.model.Article
import com.almasabdykadyr.newsapp.domain.model.Source
import com.almasabdykadyr.newsapp.presentation.Dimens.ArticleCardSize
import com.almasabdykadyr.newsapp.presentation.Dimens.ExtraSmallPadding
import com.almasabdykadyr.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.almasabdykadyr.newsapp.presentation.Dimens.SmallIconSize

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier, article: Article, onClick: () -> Unit
) {
    val context = LocalContext.current

    Row(modifier = Modifier.clickable { onClick() }) {

        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(
                    ArticleCardSize
                )
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Icon(
                    modifier = Modifier.size(SmallIconSize),
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ArticleCardPreview() {
    ArticleCard(
        article = Article(
            source = Source("engadget", "Engadget"),
            author = "Daniel Cooper",
            title = "The Morning After: Is the M3 iMac worth it?",
            description = "Unlike many of my peers, I prefer desktops to laptops, so I’m always excited when a new iMac rolls off the production line. I’ve had my eye on one for a while, especially now it’s packing an M3 chip\r\n with all the power that promises. Sadly for me and other d…",
            url = "https://www.engadget.com/the-morning-after-is-the-m3-imac-worth-it-121522249.html",
            urlToImage = "https://s.yimg.com/ny/api/res/1.2/rr2uCHkNnpizYD5xsx5alA--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2023-11/8f905dc0-7f48-11ee-bf3b-a1d7773a597c",
            publishedAt = "2023-11-13T12:15:22Z",
            content = "Unlike many of my peers, I prefer desktops to laptops, so Im always excited when a new iMac rolls off the production line. Ive had my eye on one for a while, especially now its packing anM3 chip\r\n wi… [+3194 chars]"
        )
    ) {

    }
}