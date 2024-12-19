package com.id.weatherapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.id.weatherapp.domain.usecase.GetWeatherUseCase
import com.id.weatherapp.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent

    private val _searchActive = MutableStateFlow(false)
    val searchActive: StateFlow<Boolean> = _searchActive.asStateFlow()

    init {
        loadSavedCityWeather()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _searchActive.value = query.isNotEmpty()
        if (query.isNotEmpty()) {
            searchCity(query)
        }
    }

    private fun searchCity(city: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val result = getWeatherUseCase(city)
                result.fold(
                    onSuccess = { weatherData ->
                        _uiState.value = HomeUiState.Success(weatherData)
                    },
                    onFailure = {
                        _uiState.value = HomeUiState.Error(it.message ?: "Unknown Error")
                    }
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun fetchWeatherForCity(city: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val result = getWeatherUseCase(city)
                result.fold(
                    onSuccess = { weatherData ->
                        _uiState.value = HomeUiState.Success(weatherData)
                        _searchActive.value = false
                        _searchQuery.value = ""
                    },
                    onFailure = {
                        _uiState.value = HomeUiState.Error(it.message ?: "Unknown Error")
                    }
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown Error")
            }
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

    fun onCitySelected(city: String) {
        viewModelScope.launch {
            _navigationEvent.emit(NavigationEvent.NavigateToDetail(city))
            fetchWeatherForCity(city)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        _searchActive.value = false
    }

    fun onBackPressed() {
        if (_searchActive.value) {
            clearSearch()
        } else {
            viewModelScope.launch {
                _navigationEvent.emit(NavigationEvent.NavigateBack)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        clearSearch()
    }
}