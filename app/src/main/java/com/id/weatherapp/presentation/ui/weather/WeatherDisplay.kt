package com.id.weatherapp.presentation.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.weatherapp.data.model.WeatherData

@Composable
fun WeatherDisplay(weatherData: WeatherData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = weatherData.cityName,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Temperature: ${weatherData.temperature}°C",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Condition: ${weatherData.condition}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Humidity: ${weatherData.humidity}%",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "UV Index: ${weatherData.uvIndex}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Feels like: ${weatherData.feelsLike}°C",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}