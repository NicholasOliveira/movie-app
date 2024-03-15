package br.com.movieapp.movie_favorite_feature.domain.source

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteLocalSourceData {

    suspend fun getMovie(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean

}