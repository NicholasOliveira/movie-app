package br.com.movieapp.movie_search_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.movie_search_feature.domain.usecase.GetSearchMoviesUseCase
import br.com.movieapp.movie_search_feature.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getSearchMoviesUSeCase: GetSearchMoviesUseCase
) : ViewModel() {


    var uiState by mutableStateOf(MovieSearchState())
        private set

    fun fetch(query: String = "") {

        val movies = getSearchMoviesUSeCase.invoke(
            params = GetSearchMoviesUseCase.Params(
                query = query
            )
        )
            .cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)

    }

    fun event(event: MovieSearchEvent) {
        uiState = when(event) {
            is MovieSearchEvent.EnteredQuery -> {
                uiState.copy(query = event.value)
            }
        }
    }

}