package com.cmaina.weatherapp.ui.screens.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val presentationModel: HomePresentationModel? = null,
    val errorMessage: String? = null
)
