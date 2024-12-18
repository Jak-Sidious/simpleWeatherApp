package com.id.weatherapp.presentation.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.weatherapp.domain.usecase.GetWeatherUseCase
import com.id.weatherapp.data.model.WeatherData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun fetchWeatherForCity(city: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = getWeatherUseCase(city)
            result.fold(
                onSuccess = {
                    _uiState.value = HomeUiState.Success(it)
                },
                onFailure = {
                    _uiState.value = HomeUiState.Error(it.message ?: "Unknown Error")
                }
            )
        }
    }

    fun loadSavedCityWeather() {
        viewModelScope.launch {
            val savedCity = getWeatherUseCase.getSavedCity()
            if (savedCity.isNotEmpty()) {
                fetchWeatherForCity(savedCity)
            } else {
                _uiState.value = HomeUiState.Empty
            }
        }
    }
}