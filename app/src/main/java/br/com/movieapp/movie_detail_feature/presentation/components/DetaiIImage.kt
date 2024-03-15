package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.core.util.toBackDropUrl
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailImage(modifier: Modifier = Modifier, backdropPathUrl: String) {
    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .crossfade(true)
                .error(R.drawable.ic_error_image)
                .data(backdropPathUrl?.toBackDropUrl())
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.heightIn(200.dp),
        )
    }
}

@Preview
@Composable
fun DetailImagePreview() {
    DetailImage(modifier = Modifier, backdropPathUrl = "")
}