package br.com.movieapp.movie_detail_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.Constants
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.state.MovieDetailState
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsFavoriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val removeMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isFavoriteMovie: IsFavoriteMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
     private set


    private val movieId = savedStateHandle.get<Int>(key = Constants.KEY_MOVIE_ID)

    init {
        if (movieId !== null) {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(movieId = movieId))
            isFavoriteMovie(MovieDetailEvent.IsFavoriteMovie(movieId))
        }
    }

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun isFavoriteMovie(isFavoriteMovie: MovieDetailEvent.IsFavoriteMovie) {
        event(isFavoriteMovie)
    }

    fun changeFavorite(movie: Movie) {
        UtilFunctions.logInfo("Detail", movie.toString())
        if (uiState.iconColor == Color.White) {
            event(MovieDetailEvent.AddFavoriteMovieDetail(movie = movie))
        } else {
            event(MovieDetailEvent.RemoveFavoriteMovieDetail(movie = movie))
        }
    }

    private fun event (event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    movieDetailsUseCase.invoke(params = GetMovieDetailsUseCase.Params(
                        movieId = event.movieId
                    )).collect{
                            ResultData ->
                        when (ResultData) {
                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true,
                                )
                            }
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = ResultData.data?.second,
                                    result = ResultData.data?.first
                                )
                            }
                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = ResultData.e?.message.toString()
                                )
                            }
                        }
                    }
                }
            }
            is MovieDetailEvent.AddFavoriteMovieDetail -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(params = AddMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest { ResultData ->
                        when (ResultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("Detail","Falha ao Favoritar")
                            }
                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
            is MovieDetailEvent.IsFavoriteMovie -> {
                viewModelScope.launch {
                    isFavoriteMovie.invoke(params = IsFavoriteMovieUseCase.Params(
                        movieId = event.movieId
                    )).collectLatest { result ->
                        when (result) {
                        is ResultData.Success -> {
                            uiState = if (result.data == true) {
                                uiState.copy(iconColor = Color.Red)
                            } else {
                                uiState.copy(iconColor = Color.White)
                            }
                        }
                        is ResultData.Failure -> {
                            UtilFunctions.logError("Detail","Falha ao checkar Favorito")
                        }
                        is ResultData.Loading -> {}
                    } }
                }
            }
            is MovieDetailEvent.RemoveFavoriteMovieDetail -> {
                viewModelScope.launch {
                    removeMovieFavoriteUseCase.invoke(params = DeleteMovieFavoriteUseCase.Params(
                        movie = event.movie
                    )).collectLatest {ResultData ->
                        when(ResultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }
                            is ResultData.Failure -> {
                                UtilFunctions.logError("Detail","Falha ao Remover Favorito")
                            }
                            is ResultData.Loading -> {}

                        }
                    }
                }
            }
        }
    }

}