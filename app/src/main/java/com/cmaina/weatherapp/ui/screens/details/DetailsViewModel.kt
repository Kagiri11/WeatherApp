package com.cmaina.weatherapp.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.weatherapp.domain.models.ForecastDay
import com.cmaina.weatherapp.domain.repository.SettingsRepository
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    init {
        fetchPreferredTemperatureUnit()
    }

    fun fetchForecastInfo(date: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            weatherRepository.fetchWeatherInfoForDay(date).collect { result ->
                result
                    .onSuccess { forecast ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                data = forecast
                            )
                        }
                        saveWeatherInfo(forecast)
                    }
                    .onFailure {throwable ->
                        Log.d("Dirtbag","Error: ${throwable.message}")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = throwable.message
                            )
                        }
                    }
            }
        }
    }

    private fun saveWeatherInfo(forecastDay: ForecastDay) {
        viewModelScope.launch {
            weatherRepository.saveWeatherInfo(forecastDay.copy(isFromCache = true))
        }
    }

    private fun fetchPreferredTemperatureUnit() {
        viewModelScope.launch {
            settingsRepository.shouldShowCelsius().collect { isCelsius ->
                _uiState.update { it.copy(shouldUseCelsius = isCelsius) }
            }
        }
    }
}