package com.id.weatherapp.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.id.weatherapp.presentation.navigation.NavigationEvent
import com.id.weatherapp.presentation.ui.common.EmptyState
import com.id.weatherapp.presentation.ui.common.SearchBar
import com.id.weatherapp.presentation.ui.viewmodel.HomeUiState
import com.id.weatherapp.presentation.ui.viewmodel.HomeViewModel
import com.id.weatherapp.presentation.ui.weather.WeatherDisplay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onCitySelected: (String) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isSearchActive by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                is NavigationEvent.NavigateToDetail -> onCitySelected(event.city)
                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            query = viewModel.searchQuery.collectAsStateWithLifecycle().value,
            onQueryChange = {
                viewModel.onSearchQueryChanged(it)
                isSearchActive = it.isNotEmpty()
            },
            onSearch = {
                viewModel.fetchWeatherForCity(it)
                isSearchActive = false
            },
            modifier = Modifier.padding(16.dp)
        )

        when {
            isSearchActive && uiState is HomeUiState.Success -> {
                WeatherDisplay(
                    weatherData = (uiState as HomeUiState.Success).weatherData,
                    isSearchResult = true,
                    onClick = {
                        onCitySelected((uiState as HomeUiState.Success).weatherData.cityName)
                        isSearchActive = false
                    }
                )
            }
            uiState is HomeUiState.Loading -> CircularProgressIndicator()
            uiState is HomeUiState.Success -> WeatherDisplay((uiState as HomeUiState.Success).weatherData)
            uiState is HomeUiState.Error -> {} // Empty composable for error state
            uiState is HomeUiState.Empty -> EmptyState("No City Selected", "Please Search For A City")
        }
    }
}
