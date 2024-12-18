package com.id.weatherapp.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.id.weatherapp.presentation.ui.viewmodel.HomeUiState
import com.id.weatherapp.presentation.ui.viewmodel.HomeViewModel
import com.id.weatherapp.presentation.ui.weather.WeatherDisplay

@Composable
fun HomeScreen(viewModel: HomeViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = viewModel.searchQuery.collectAsStateWithLifecycle().value,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            label = { Text("Search location") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.fetchWeatherForCity(viewModel.searchQuery.value) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }

        when (uiState) {
            is HomeUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            is HomeUiState.Success -> WeatherDisplay((uiState as HomeUiState.Success).weatherData)
            is HomeUiState.Error -> Text("")
            is HomeUiState.Empty -> Text("Search for a city to see weather details.")
        }
    }
}
