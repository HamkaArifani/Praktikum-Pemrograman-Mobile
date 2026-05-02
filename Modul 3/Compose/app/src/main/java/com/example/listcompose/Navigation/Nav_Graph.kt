package com.example.listcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listcompose.Layout.DetailScreen
import com.example.listcompose.Layout.HomeScreen
import com.example.listcompose.Layout.LanguageScreen
import com.example.listcompose.ViewModel.FilmViewModel

@Composable
fun Nav_Graph(
    navController: NavHostController,
    viewModel: FilmViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "detail/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val filmId = backStackEntry.arguments?.getInt("filmId") ?: 0
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