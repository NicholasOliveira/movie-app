package br.com.movieapp.movie_detail_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MoviesSimilarPagingSource
import br.com.movieapp.core.util.toBackDropUrl
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import java.text.SimpleDateFormat
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(private val service: MovieService) :
    MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres?.map {
            it.name
        }

        val originalDate = SimpleDateFormat("yyyy-MM-dd").parse(response.releaseDate)
        val formattedDate = SimpleDateFormat("dd/MM/yyyy").format(originalDate)

        return MovieDetails(
            id = response.id,
            genres = genres ?: listOf(),
            title = response.title,
            overview = response.overview,
            releaseDate = formattedDate,
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount,
            backdropPathUrl = response.backdropPath.toBackDropUrl()
        )
    }

    override suspend fun getMoviesSimilar(movieId: Int, page: Int): MovieResponse {
        return service.getMovieSimilar(movieId = movieId, page = page)
    }

    override fun getMoviesSimilarPagingSource(movieId: Int): MoviesSimilarPagingSource {
        return MoviesSimilarPagingSource(movieId = movieId, this)
    }
}