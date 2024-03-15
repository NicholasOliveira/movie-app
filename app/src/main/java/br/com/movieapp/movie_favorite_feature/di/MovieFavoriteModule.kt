package br.com.movieapp.movie_favorite_feature.di

import androidx.room.PrimaryKey
import br.com.movieapp.core.data.local.MovieDAO
import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalSourceDataImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalSourceData
import br.com.movieapp.movie_favorite_feature.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource (movieDAO: MovieDAO): MovieFavoriteLocalSourceData {
        return MovieFavoriteLocalSourceDataImpl(movieDao = movieDAO)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(movieFavoriteLocalSourceData: MovieFavoriteLocalSourceData): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(movieFavoriteLocalSourceData = movieFavoriteLocalSourceData)
    }

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteMovieFavoriteUseCase(repository: MovieFavoriteRepository): IsFavoriteMovieUseCase {
        return IsFavoriteMovieUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieFavoriteUseCase(repository: MovieFavoriteRepository): GetMovieFavoriteUseCase {
        return GetMovieFavoriteUseCaseImpl(repository)
    }

}