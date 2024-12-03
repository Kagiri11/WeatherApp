package com.cmaina.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.cmaina.weatherapp.ui.screens.home.HomeViewModel
import com.cmaina.weatherapp.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeUiState by viewModel.uiState.collectAsState()
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {

                        Column {
                            Text("Temperature: ${homeUiState.currentWeatherInfo?.temperatureInCelsius}")
                            Text("Humidity: ${homeUiState.currentWeatherInfo?.humidity}")
                            Text("Wind speed: ${homeUiState.currentWeatherInfo?.windSpeedInKilometersPerHour}")
                        }

                    }
                }
            }
        }
    }
}