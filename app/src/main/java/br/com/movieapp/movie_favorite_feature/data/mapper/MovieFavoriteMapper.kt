package br.com.movieapp.movie_favorite_feature.data.mapper

import br.com.movieapp.core.data.local.MovieEntity
import br.com.movieapp.core.domain.model.Movie

fun Movie.toEntity() = MovieEntity(
    movieId = id,
    title = title,
    imageUrl = imageUrl
)

fun List<MovieEntity>.toMovie() = map {movieEntity ->
   Movie(
       id = movieEntity.movieId,
       title = movieEntity.title,
       imageUrl = movieEntity.imageUrl
   )
}