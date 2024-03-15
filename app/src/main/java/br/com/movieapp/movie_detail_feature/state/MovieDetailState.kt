package br.com.movieapp.movie_detail_feature.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState(
    val isLoading: Boolean = false,
    val error: String  = "",
    val iconColor: Color = Color.White,
    val movieDetails: MovieDetails? = null,
    val result: Flow<PagingData<Movie>>? = emptyFlow()
)