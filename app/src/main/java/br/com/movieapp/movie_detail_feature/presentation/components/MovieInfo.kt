package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R

@Composable
fun MovieInfoGroup(modifier: Modifier = Modifier, voteCount: Int, duration: Int, releaseDate: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieInfo(
            modifier = Modifier,
            title = stringResource(id = R.string.vote_average),
            subTitle = voteCount.toString()
        )
        MovieInfo(
            modifier = Modifier,
            title = stringResource(id = R.string.duration),
            subTitle = stringResource(id = R.string.duration_minutes, duration)
        )
        MovieInfo(
            modifier = Modifier,
            title = stringResource(id = R.string.release_date),
            subTitle = releaseDate
        )
    }
}

@Composable
fun MovieInfo(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {
    Column(
        modifier = modifier.padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            color = Color.DarkGray,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.body2,
            color = Color.DarkGray,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
@Composable
fun MovieInfoPreview() {
    MovieInfoGroup(modifier = Modifier,
        releaseDate = "2012-04-01",
        voteCount = 20,
        duration = 0,)
}