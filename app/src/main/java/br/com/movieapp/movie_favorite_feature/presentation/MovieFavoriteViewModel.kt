package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.presentation.state.MovieFavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetMovieFavoriteUseCase
): ViewModel() {

    var uiState by mutableStateOf(MovieFavoriteState())
        private set


    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            getFavoriteUseCase.invoke().collectLatest { movies ->
                uiState = uiState.copy(movies = movies)
            }
        }
    }


}