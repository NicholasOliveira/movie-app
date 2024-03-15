package br.com.movieapp.movie_detail_feature.presentation.components

import RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.util.toPostUrl
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent(
    modifier: Modifier,
    movieDetails: MovieDetails,
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    iconColor: Color,
    onGoMovieDetail: (movieId: Int) -> Unit,
    onAddFavorite: (Movie) -> Unit
) {

    Box(
        modifier = modifier
            .background(black)
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DetailImage(backdropPathUrl = movieDetails.backdropPathUrl ?: "")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    onAddFavorite(Movie(
                        id = movieDetails.id,
                        title = movieDetails.title,
                        imageUrl = movieDetails.backdropPathUrl.toPostUrl()
                    ))
                }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
            )

            Text(
                text = movieDetails.title,
                color = white,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
            )

            FlowRow(
                mainAxisSpacing = 10.dp,
                mainAxisAlignment = MainAxisAlignment.Center,
                crossAxisSpacing = 10.dp

            ) {
                movieDetails.genres.map {
                    GenreTag(genre = it)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            MovieInfoGroup(
                voteCount = movieDetails.voteCount,
                duration = movieDetails.duration,
                releaseDate = movieDetails.releaseDate ?: "",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            RatingBar(
                rating = movieDetails.voteAverage.toFloat()?.div(2f) ?: 0f,
                modifier = Modifier
                    .height(20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Overview(overview = movieDetails.overview ?: "")


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Text(
                text = stringResource(id = R.string.movies_similar),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )

            MovieSimilar(
                moviesPaging = pagingMoviesSimilar,
                onDetails = {
                    onGoMovieDetail(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

        }
    }

}

var movieDetails = MovieDetails(
    id = 34391,
    title = "Ultimate Homem-Aranha",
    genres = listOf(
        "Aventura",
        "Ação",
        "Terror",
        "Animação",
        "Suspense",
        "Dorama",
        "Kids",
        "Drama",
        "Policial"
    ),
    overview =
    "Peter Parker tornou-se Homem-Aranha há dois anos, e Nick Fury, diretor da S.H.I.E.L.D., oferece a Peter a chance de treinar e se tornar o \"Ultimate Homem-Aranha\", trabalhando ao lado de quatro colegas super-heróis adolescentes.",
    backdropPathUrl = "/gXeCzYmCRBlpbbhhKrYM1ZpIDAA.jpg",
    releaseDate = "2012-04-01",
    voteAverage = 7.666,
    duration = 100,
    voteCount = 20,

    )

@Preview
@Composable
fun MovieDetailContentPreview() {
    MovieDetailContent(
        modifier = Modifier,
        movieDetails = movieDetails,
        pagingMoviesSimilar = flowOf(
            PagingData.from(
                listOf(
                    Movie(
                        id = 1,
                        title = "Titulo",
                        voteAverage = 10.0,
                        imageUrl = ""
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        isLoading = false,
        isError = "Error",
        iconColor = Color.Red,
        onGoMovieDetail = {},
        onAddFavorite = {}
    )
}