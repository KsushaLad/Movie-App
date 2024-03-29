package com.ladoshko.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ladoshko.moviesapp.MainViewModel
import com.ladoshko.moviesapp.screens.DetailsScreen
import com.ladoshko.moviesapp.screens.MainScreen
import com.ladoshko.moviesapp.screens.SplashScreen
import com.ladoshko.moviesapp.utils.Constans

sealed class Screens(val route: String){
    object Splash: Screens(route = Constans.Screens.SPLASH_SCREEN)
    object Main: Screens(route = Constans.Screens.MAIN_SCREEN)
    object Details: Screens(route = Constans.Screens.DETAILS_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Details.route + "/{Id}") { backStackEntry ->
            DetailsScreen(viewModel = viewModel, itemId = backStackEntry.arguments?.getString("Id")?: "1")
        }
    }

}