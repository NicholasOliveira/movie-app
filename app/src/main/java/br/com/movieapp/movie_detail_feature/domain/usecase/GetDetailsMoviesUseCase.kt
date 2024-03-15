package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(private val repository: MovieDetailsRepository) : GetMovieDetailsUseCase {

    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val moviesDetails = repository.getMovieDetail(params.movieId)
                val moviesSimilar = repository.getMovieSimilar(params.movieId, pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                ))
                emit(ResultData.Success(
                    moviesSimilar to moviesDetails
                ))
            } catch (exception: IOException) {
                emit(ResultData.Failure(exception))
            }  catch (exception: HttpException) {
                emit(ResultData.Failure(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}