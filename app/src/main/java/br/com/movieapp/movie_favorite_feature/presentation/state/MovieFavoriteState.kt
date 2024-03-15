package br.com.movieapp.movie_favorite_feature.presentation.state

import br.com.movieapp.core.domain.model.Movie

data class MovieFavoriteState(
    val movies: List<Movie> = emptyList()
)
