package com.id.weatherapp.presentation.ui.weather

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.id.weatherapp.R

import java.util.Locale

@Composable
fun WeatherIcon(condition: String, size: Dp = 24.dp) {
    val iconRes = when (condition.lowercase(Locale.ROOT)) {
        "sunny", "clear" -> R.drawable.ic_sunny
        "partly cloudy", "cloudy" -> R.drawable.ic_partly_cloudy
        "overcast" -> R.drawable.ic_cloudy
        "rain", "light rain", "moderate rain" -> R.drawable.ic_rainy
        else -> R.drawable.ic_sunny // Default icon
    }

    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = condition,
        modifier = Modifier.size(size),
        tint = Color.Unspecified
    )
}