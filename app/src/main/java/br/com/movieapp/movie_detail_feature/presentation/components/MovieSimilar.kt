package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.core.util.toPostUrl
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem
import kotlin.math.max

@Composable
fun MovieSimilar(
    moviesPaging: LazyPagingItems<Movie>,
    onDetails: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(moviesPaging.itemCount) { index ->
            val movie = moviesPaging[index]
            movie?.let {
                with(it) {
                    MovieItem(
                        modifier = Modifier.height(200.dp),
                        voteAverage = voteAverage,
                        imageUrl = imageUrl.toPostUrl(),
                        id = id,
                        onClick = { movieId ->
                            onDetails(movieId)
                        }
                    )
                }
            }
        }
        moviesPaging.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item() {
                        LoadingView()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item()
                    {
                        LoadingView()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item() {
                        ErrorScreen(message = "Verifique sua conexão com a internet.", retry = {
                            retry()
                        })
                    }
                }
                loadState.append is LoadState.Error -> {
                    item() {
                        ErrorScreen(
                            message = "Verifique sua conexão com a internet.",
                            retry = {
                                retry()
                            })
                    }
                }
            }
        }
    }
}