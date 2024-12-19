package com.id.weatherapp.presentation.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.id.weatherapp.data.model.WeatherData
import com.id.weatherapp.presentation.ui.common.MetricsCard

@Composable
fun FullWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = weatherData.cityName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        WeatherIcon(
            condition = weatherData.condition,
            size = 120.dp
        )

        Text(
            text = "${weatherData.temperature.toInt()}°",
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 72.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        MetricsCard(weatherData)
    }
}