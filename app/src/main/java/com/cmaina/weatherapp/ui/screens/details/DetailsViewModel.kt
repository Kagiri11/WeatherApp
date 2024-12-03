package com.cmaina.weatherapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun fetchForecastInfo(date: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            weatherRepository.fetchWeatherInfoForDay(date).collect { result ->
                result
                    .onSuccess { forecastInfo ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                data = forecastInfo
                            )
                        }
                    }
                    .onFailure {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = it.errorMessage
                            )
                        }
                    }
            }
        }
    }
}