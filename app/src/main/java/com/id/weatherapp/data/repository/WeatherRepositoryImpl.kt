package com.id.weatherapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.id.weatherapp.BuildConfig
import com.id.weatherapp.data.api.WeatherApiService
import com.id.weatherapp.data.model.WeatherData
import com.id.weatherapp.data.model.WeatherResponse
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApiService,
    private val dataStore: DataStore<Preferences>
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Result<WeatherData> {
        return try {
            val response = weatherApiService.getCurrentWeather(BuildConfig.WEATHER_API_KEY, city)
            if (response.isSuccessful) {
                val weatherResponse = response.body()
                if (weatherResponse != null) {
                    saveSelectedCity(city)
                    Result.success(weatherResponse.toWeatherData())
                } else Result.failure(Exception("Empty response body"))
            } else Result.failure(Exception("API request failed: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSelectedCity(): String {
        return dataStore.data.map { it[CITY_KEY] ?: "" }.firstOrNull() ?: ""
    }

    private suspend fun saveSelectedCity(city: String) {
        dataStore.edit { it[CITY_KEY] = city }
    }

    private fun WeatherResponse.toWeatherData() = WeatherData(
        cityName = location.name,
        temperature = current.temp_c,
        condition = current.condition.text,
        humidity = current.humidity,
        uvIndex = current.uv,
        feelsLike = current.feelslike_c
    )

    companion object {
        private val CITY_KEY = stringPreferencesKey("selected_city")
    }
}