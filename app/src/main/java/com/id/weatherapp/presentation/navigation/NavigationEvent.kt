package com.id.weatherapp.presentation.navigation

sealed class NavigationEvent {
    data class NavigateToDetail(val city: String) : NavigationEvent()
    object NavigateBack : NavigationEvent()
}