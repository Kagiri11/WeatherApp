package com.cmaina.weatherapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentWeatherInfo()
    }

    private fun fetchCurrentWeatherInfo() {
        updateLoadingStatus(true)
        viewModelScope.launch {
            weatherRepository.fetchCurrentWeatherInfo().collect { result ->
                result
                    .onSuccess { forecastInfo ->
                        Log.d("Dirtbag", "on success: $forecastInfo")
                        updateLoadingStatus(false)
                        _uiState.update { it.copy(currentWeatherInfo = forecastInfo.currentWeatherInfo) }
                    }
                    .onFailure { throwable ->
                        updateLoadingStatus(false)
                        Log.d("Dirtbag", "on failure: ${throwable.message}")
                        _uiState.update { it.copy(errorMessage = throwable.message) }
                    }
            }
        }
    }

    private fun updateLoadingStatus(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }
}