package br.com.movieapp.movie_search_feature.presentation.state

import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState (
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()
)