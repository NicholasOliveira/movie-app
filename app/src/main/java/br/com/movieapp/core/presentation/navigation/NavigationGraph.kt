package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.Constants
import br.com.movieapp.movie_detail_feature.MovieDetailScreen
import br.com.movieapp.movie_detail_feature.MovieDetailViewModel
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.movie_search_feature.presentation.MovieSearchEvent
import br.com.movieapp.movie_search_feature.presentation.MovieSearchScreen
import br.com.movieapp.movie_search_feature.presentation.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.MoviePopular.route) {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MoviePopularScreen(
                uiState = uiState,
                navigationToDetailMovie = {
                    navController.navigate(BottomNavItem.MovieDetail.setArgumentsMovieId(movieId = it))
                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieSearchScreen(
                uiState = uiState,
                onEvent  = viewModel::event,
                onFetch  = viewModel::fetch,
                navigateToDetailMovie = {
                    navController.navigate(BottomNavItem.MovieDetail.setArgumentsMovieId(movieId = it))
                }
            )

        }
        composable(BottomNavItem.MovieFavorite.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieFavoriteScreen(uiState = uiState, navigateToDetailMovie = {
                navController.navigate(BottomNavItem.MovieDetail.setArgumentsMovieId(movieId = it))
            })
        }

        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
            navArgument(Constants.KEY_MOVIE_ID) {
                type = NavType.IntType
                defaultValue = 0
            }
        )) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieDetailScreen(
                uiState = uiState,
                changeFavorite = viewModel::changeFavorite,
                navigateToDetailMovie = {movieId ->
                    navController.navigate(BottomNavItem.MovieDetail.setArgumentsMovieId(movieId = movieId))
                }
            )
        }
    }
}