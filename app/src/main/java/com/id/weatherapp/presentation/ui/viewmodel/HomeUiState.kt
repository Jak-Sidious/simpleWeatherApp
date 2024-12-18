package com.id.weatherapp.presentation.ui.viewmodel

import com.id.weatherapp.data.model.WeatherData

sealed class HomeUiState {
    object Empty : HomeUiState()
    object Loading : HomeUiState()
    data class Success(val weatherData: WeatherData) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}