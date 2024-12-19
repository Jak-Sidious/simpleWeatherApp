package com.id.weatherapp.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.id.weatherapp.data.model.WeatherData
import com.id.weatherapp.presentation.ui.weather.WeatherMetric


@Composable
fun MetricsCard(weatherData: WeatherData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WeatherMetric("Humidity", "${weatherData.humidity}%")
            WeatherMetric("UV", weatherData.uvIndex.toString())
            WeatherMetric("Feels like", "${weatherData.feelsLike.toInt()}°")
        }
    }
}