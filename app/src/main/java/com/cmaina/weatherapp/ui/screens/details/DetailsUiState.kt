package com.cmaina.weatherapp.ui.screens.details

import com.cmaina.weatherapp.domain.models.ForecastInfo

data class DetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val data: ForecastInfo? = null
)