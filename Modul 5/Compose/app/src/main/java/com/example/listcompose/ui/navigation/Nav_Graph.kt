package com.example.listcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.listcompose.data.DataSource
import com.example.listcompose.ui.screen.detail.DetailScreen
import com.example.listcompose.ui.screen.home.HomeScreen
import com.example.listcompose.ui.screen.language.LanguageScreen
import com.example.listcompose.ui.screen.home.HomeViewModel
import com.example.listcompose.ui.screen.home.HomeViewModelFactory
import com.example.listcompose.R
import com.example.listcompose.ui.screen.detail.DetailViewModel
import com.example.listcompose.ui.screen.detail.DetailViewModelFactory

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
            val factory = HomeViewModelFactory(DataSource(), stringResource(R.string.homepage))
            val viewModel : HomeViewModel = viewModel(factory = factory)
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