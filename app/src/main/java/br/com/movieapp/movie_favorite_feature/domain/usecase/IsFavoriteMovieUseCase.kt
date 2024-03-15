package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsFavoriteMovieUseCase {

    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(
        val movieId: Int
    )

}

class IsFavoriteMovieUseCaseImpl @Inject constructor(private val repository: MovieFavoriteRepository) : IsFavoriteMovieUseCase {

    override suspend operator fun invoke(params: IsFavoriteMovieUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            val response = repository.isFavorite(movieId = params.movieId)
            emit(ResultData.Success(response))
        }.flowOn(Dispatchers.IO)
    }
}