package com.id.weatherapp.data.repository

import com.id.weatherapp.data.model.WeatherData

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Result<WeatherData>
    suspend fun getSelectedCity(): String
}