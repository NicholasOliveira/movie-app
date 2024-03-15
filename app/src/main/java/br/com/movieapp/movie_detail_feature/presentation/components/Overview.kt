package br.com.movieapp.movie_detail_feature.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.R
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Overview(modifier: Modifier = Modifier, overview: String) {

    var isOpen by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.body1,
            color = white
        )
        if (isOpen) {
            Text(
                text = overview,
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    isOpen = !isOpen
                }
            )
        } else {
            Text(
                text = overview,
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable {
                    isOpen = !isOpen
                }
            )
        }
    }

}

@Preview
@Composable
fun OverviewPreview() {
    Overview(
        modifier = Modifier,
        overview = "asdlaskd lkaldkasldk asldkasldka asdlaskd lkaldkasldk asldkasldka asdlaskd lkaldkasldk asldkasldka asdlaskd lkaldkasldk asldkasldka asdlaskd lkaldkasldk asldkasldka"
    )
}