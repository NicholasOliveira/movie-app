package br.com.movieapp.movie_favorite_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.toPostUrl
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieFavoriteItem(movie: Movie, modifier: Modifier = Modifier, onClick: (Int) -> Unit) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick(movie.id)
        }) {
        Column(modifier = modifier.background(black)) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .error(R.drawable.ic_error_image)
                    .placeholder(R.drawable.ic_placeholder)
                    .data(movie.imageUrl.toPostUrl())
                    .build(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Text(
                text = movie.title,
                color = white,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

}


@Preview
@Composable
fun MovieFavoriteItemPreview() {
    MovieFavoriteItem(
        Movie(
            id = 1,
            title = "Titutlo do filme",
            imageUrl = ""
        ), modifier = Modifier,
        onClick = {}
    )
}