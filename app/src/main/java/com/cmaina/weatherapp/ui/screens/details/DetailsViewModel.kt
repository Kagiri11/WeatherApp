package com.cmaina.weatherapp.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.weatherapp.domain.models.ForecastDay
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
                    .onSuccess { forecast ->
                        Log.d("Dirtbag", "fetchForecastInfo: $forecast")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                data = forecast
                            )
                        }
                        saveWeatherInfo(forecast)
                    }
                    .onFailure {
                        Log.d("Dirtbag", "failure: $it")
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

    private fun saveWeatherInfo(forecastDay: ForecastDay) {
        viewModelScope.launch {
            weatherRepository.saveWeatherInfo(forecastDay.copy(isFromCache = true))
        }
    }
}