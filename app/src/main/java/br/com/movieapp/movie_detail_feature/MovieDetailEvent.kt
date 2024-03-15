package br.com.movieapp.movie_detail_feature

import br.com.movieapp.core.domain.model.Movie

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
    data class AddFavoriteMovieDetail(val movie: Movie) : MovieDetailEvent()
    data class RemoveFavoriteMovieDetail(val movie: Movie) : MovieDetailEvent()
    data class IsFavoriteMovie(val movieId: Int) : MovieDetailEvent()

}
