package br.com.movieapp.movie_search_feature.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.movie_search_feature.data.repository.MovieSearchRepositoryImpl
import br.com.movieapp.movie_search_feature.data.source.MovieSearchRemoteDataSourceImpl
import br.com.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import br.com.movieapp.movie_search_feature.domain.usecase.GetSearchMoviesUseCase
import br.com.movieapp.movie_search_feature.domain.usecase.GetSearchMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieSearchFeatureModule {

    @Provides
    @Singleton
    fun getSearchMovieRemoteDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun getSearchMovieRepository(movieSearchRemoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(movieSearchRemoteDataSource)
    }

    @Provides
    @Singleton
    fun GetSearchMoviesUseCase(repository: MovieSearchRepository): GetSearchMoviesUseCase {
        return GetSearchMoviesUseCaseImpl(repository)
    }
}