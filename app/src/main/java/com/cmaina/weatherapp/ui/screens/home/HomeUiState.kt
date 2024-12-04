package com.cmaina.weatherapp.ui.screens.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val presentationModel: HomePresentationModel? = null,
    val shouldUseCelsius: Boolean? = null,
    val errorMessage: String? = null
)
