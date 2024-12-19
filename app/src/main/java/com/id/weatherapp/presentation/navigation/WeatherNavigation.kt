package com.id.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.id.weatherapp.presentation.ui.home.HomeScreen
import com.id.weatherapp.presentation.ui.viewmodel.HomeViewModel
import com.id.weatherapp.presentation.ui.weather.WeatherDetailScreen


@Composable
fun WeatherNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onCitySelected = { city ->
                    navController.navigate(Screen.WeatherDetail.createRoute(city))
                }
            )
        }

        composable(
            route = Screen.WeatherDetail.route
        ) { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city") ?: return@composable
            WeatherDetailScreen(
                viewModel = viewModel,
                city = city,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}