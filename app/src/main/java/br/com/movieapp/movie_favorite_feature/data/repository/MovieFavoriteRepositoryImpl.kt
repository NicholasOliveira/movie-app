package br.com.movieapp.movie_favorite_feature.data.repository

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalSourceData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(private val movieFavoriteLocalSourceData: MovieFavoriteLocalSourceData) : MovieFavoriteRepository {
    override suspend fun getMovie(): Flow<List<Movie>> {
        return movieFavoriteLocalSourceData.getMovie()
    }

    override suspend fun insert(movie: Movie) {
        return movieFavoriteLocalSourceData.insert(movie = movie)
    }

    override suspend fun delete(movie: Movie) {
        return movieFavoriteLocalSourceData.delete(movie = movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieFavoriteLocalSourceData.isFavorite(movieId = movieId)
    }

}