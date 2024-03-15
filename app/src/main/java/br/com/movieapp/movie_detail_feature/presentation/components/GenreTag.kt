package br.com.movieapp.movie_detail_feature.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.ui.theme.white

@Composable
fun GenreTag(modifier: Modifier = Modifier, genre: String) {
    Box(modifier = modifier
        .border(width = 1.dp, shape = RoundedCornerShape(100.dp), color = white)
        .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = genre, color = white)
    }
}

@Preview
@Composable
fun GenreTagPreview() {
    GenreTag(modifier = Modifier, genre = "Aventura")
}