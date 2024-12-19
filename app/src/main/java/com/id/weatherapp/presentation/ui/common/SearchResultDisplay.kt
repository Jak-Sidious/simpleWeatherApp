package com.id.weatherapp.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.id.weatherapp.data.model.WeatherData
import com.id.weatherapp.presentation.ui.weather.WeatherIcon

@Composable
fun SearchResultDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = weatherData.cityName,
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WeatherIcon(condition = weatherData.condition)
            Text(
                text = "${weatherData.temperature.toInt()}°",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}