package com.id.weatherapp.data.model

data class WeatherData(
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val humidity: Int,
    val uvIndex: Double,
    val feelsLike: Double
)
