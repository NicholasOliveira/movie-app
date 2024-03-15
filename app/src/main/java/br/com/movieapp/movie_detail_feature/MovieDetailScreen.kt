package br.com.movieapp.movie_detail_feature

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import br.com.movieapp.movie_detail_feature.state.MovieDetailState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    navigateToDetailMovie: (movieId: Int) -> Unit,
    changeFavorite: (Movie) -> Unit
) {

    val pagingMoviesSimilar = uiState.result?.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.background(black).fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = black,
                contentColor = white,
            ){
               Text(text = stringResource(id = R.string.detail_movie), color = white)
            }
        }
    ) {
        uiState.movieDetails?.let { movieDetail ->
            if (pagingMoviesSimilar != null) {
                MovieDetailContent(
                    modifier = Modifier,
                    movieDetails = movieDetail,
                    pagingMoviesSimilar = pagingMoviesSimilar,
                    isLoading = uiState.isLoading,
                    isError = uiState.error,
                    iconColor = uiState.iconColor,
                    onGoMovieDetail = {
                        navigateToDetailMovie(it)
                    },
                    onAddFavorite = {movie ->
                        changeFavorite(movie)
                    }
                )
            }
        }
    }

}