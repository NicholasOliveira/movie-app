package br.com.movieapp.movie_favorite_feature.data.source

import br.com.movieapp.core.data.local.MovieDAO
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_favorite_feature.data.mapper.toEntity
import br.com.movieapp.movie_favorite_feature.data.mapper.toMovie
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalSourceData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalSourceDataImpl @Inject constructor(private val movieDao: MovieDAO) :
    MovieFavoriteLocalSourceData {
    override suspend fun getMovie(): Flow<List<Movie>> {
        return movieDao.getMovie().map {
            it.toMovie()
        }
    }

    override suspend fun insert(movie: Movie) {
        UtilFunctions.logInfo(
            "LocalSource", movie
                .toString()
        )
        return movieDao.insertFavorite(movie = movie.toEntity())
    }

    override suspend fun delete(movie: Movie) {
        return movieDao.deleteFavorite(movie = movie.toEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.isFavoriteMovie(movieId = movieId) != null
    }
}