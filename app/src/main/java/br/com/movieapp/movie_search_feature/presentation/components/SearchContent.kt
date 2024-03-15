package br.com.movieapp.movie_search_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.core.util.toPostUrl
import br.com.movieapp.movie_popular_feature.presentation.components.MovieItem
import br.com.movieapp.movie_search_feature.presentation.MovieSearchEvent
import br.com.movieapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = {
                onEvent(it)
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.heightIn(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl.toPostUrl(),
                        id = it.id,
                        onClick = { movieId ->
                            onDetail(movieId)
                        }
                    )
                }
                isLoading = false
            }
                pagingMovies.apply {
                    when {
                        isLoading -> {
                            item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                LoadingView()
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            isLoading = false
                            item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                ErrorScreen(message = "Verifique sua conexão com a internet.", retry = {
                                    retry()
                                })
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            isLoading = false
                            item(
                                span = {
                                    GridItemSpan(maxLineSpan)
                                }
                            ) {
                                ErrorScreen(
                                    message = "Verifique sua conexão com a internet.",
                                    retry = {
                                        retry()
                                    })
                            }
                        }
                    }
                }
            }

        }

    }