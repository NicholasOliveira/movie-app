package br.com.movieapp.core.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.core.data.local.MovieDataBase
import br.com.movieapp.core.data.local.MovieDAO
import br.com.movieapp.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDataBase::class.java,
        Constants.MOVIE_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideMovieDAO(dataBase: MovieDataBase): MovieDAO {
        return dataBase.movieDAO()
    }
}