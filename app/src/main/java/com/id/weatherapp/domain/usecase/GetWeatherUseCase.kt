package com.id.weatherapp.domain.usecase

import com.id.weatherapp.data.model.WeatherData
import com.id.weatherapp.data.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: String): Result<WeatherData> {
        return weatherRepository.getCurrentWeather(city)
    }

    suspend fun getSavedCity(): String {
        return weatherRepository.getSelectedCity()
    }
}