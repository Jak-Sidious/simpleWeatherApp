package com.id.weatherapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object WeatherDetail : Screen("weather/{city}") {
        fun createRoute(city: String) = "weather/$city"
    }
}