package com.cmaina.weatherapp.ui.screens.home

import com.cmaina.weatherapp.domain.models.CurrentWeatherInfo

data class HomeUiState(
    val isLoading: Boolean = false,
    val currentWeatherInfo: CurrentWeatherInfo? = null,
    val errorMessage: String? = null
)
