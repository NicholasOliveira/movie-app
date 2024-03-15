package br.com.movieapp.movie_search_feature.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetSearchMoviesUseCase {

    operator fun invoke(
        params: Params
    ): Flow<PagingData<MovieSearch>>

    data class Params(val query: String = "")
}

class GetSearchMoviesUseCaseImpl @Inject constructor(private val repository: MovieSearchRepository) :
    GetSearchMoviesUseCase {
    override fun invoke(
        params: GetSearchMoviesUseCase.Params
    ): Flow<PagingData<MovieSearch>>
    {
        return repository.getSearchMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }

}