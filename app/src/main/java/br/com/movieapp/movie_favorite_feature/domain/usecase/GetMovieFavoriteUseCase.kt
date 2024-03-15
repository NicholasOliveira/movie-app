package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMovieFavoriteUseCase {

    suspend operator fun invoke(): Flow<List<Movie>>

}

class GetMovieFavoriteUseCaseImpl @Inject constructor(private val repository: MovieFavoriteRepository) : GetMovieFavoriteUseCase {

      override suspend operator fun invoke(): Flow<List<Movie>> {
          return repository.getMovie()
      }
}