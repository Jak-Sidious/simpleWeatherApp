package com.id.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.id.weatherapp.presentation.navigation.WeatherNavigation
import com.id.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.id.weatherapp.presentation.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                WeatherNavigation(viewModel = viewModel)
            }
        }
    }
}