package com.id.weatherapp.presentation.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.id.weatherapp.presentation.ui.common.EmptyState
import com.id.weatherapp.presentation.ui.common.LoadingIndicator
import com.id.weatherapp.presentation.ui.viewmodel.HomeUiState
import com.id.weatherapp.presentation.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    viewModel: HomeViewModel,
    city: String,
    onBackClick: () -> Unit
) {
    LaunchedEffect(city) {
        viewModel.fetchWeatherForCity(city)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Weather") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        when (uiState) {
            is HomeUiState.Loading -> LoadingIndicator()
            is HomeUiState.Success -> WeatherDisplay((uiState as HomeUiState.Success).weatherData)
            is HomeUiState.Error -> {} // Empty for error state
            is HomeUiState.Empty -> EmptyState("No Data Available")
        }
    }
}