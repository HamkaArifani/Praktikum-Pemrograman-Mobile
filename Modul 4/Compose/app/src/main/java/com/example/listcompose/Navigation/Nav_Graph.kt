package com.example.listcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listcompose.Data.DataSource
import com.example.listcompose.Layout.DetailScreen
import com.example.listcompose.Layout.HomeScreen
import com.example.listcompose.Layout.LanguageScreen
import com.example.listcompose.ViewModel.FilmViewModel
import com.example.listcompose.ViewModel.FilmViewModelFactory
import com.example.listcompose.R
import com.example.listcompose.ViewModel.DetailViewModel
import com.example.listcompose.ViewModel.DetailViewModelFactory

@Composable
fun Nav_Graph(
    navController: NavHostController,
    startDestination : String = "home"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            val factory = FilmViewModelFactory(DataSource(), stringResource(R.string.homepage))
            val viewModel : FilmViewModel = viewModel(factory = factory)
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "detail/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId") ?: 0

            val factory = DetailViewModelFactory(DataSource(), filmId, stringResource(R.string.detailpage))
            val viewModel : DetailViewModel = viewModel(factory = factory)
            DetailScreen(
                filmId = filmId,
                viewModel = viewModel,
                navController = navController
            )
        }
        composable("language") {
            LanguageScreen(navController = navController)
        }
    }
}