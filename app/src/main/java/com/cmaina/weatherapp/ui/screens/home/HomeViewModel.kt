package com.cmaina.weatherapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.weatherapp.domain.models.CurrentForecastInfo
import com.cmaina.weatherapp.domain.repository.SettingsRepository
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentWeatherInfo()
        fetchPreferredTemperatureUnit()
    }

    fun fetchCurrentWeatherInfo() {
        updateLoadingStatus(true)
        viewModelScope.launch {
            weatherRepository.fetchCurrentWeatherInfo().collect { result ->
                result
                    .onSuccess { forecastInfo ->
                        updateLoadingStatus(false)
                        _uiState.update { it.copy(presentationModel = forecastInfo.toPresentationModel()) }
                    }
                    .onFailure { throwable ->
                        updateLoadingStatus(false)
                        _uiState.update { it.copy(errorMessage = throwable.message) }
                    }
            }
        }
    }

    fun changeTemperatureUnit(useCelsius: Boolean) {
        viewModelScope.launch {
            settingsRepository.setTemperatureUnit(useCelsius)
        }
    }

    fun fetchPreferredTemperatureUnit() {
        viewModelScope.launch {
            settingsRepository.shouldShowCelsius().collect { isCelsius ->
                _uiState.update { it.copy(shouldUseCelsius = isCelsius) }
            }
        }
    }

    private fun updateLoadingStatus(value: Boolean) {
        _uiState.update { it.copy(isLoading = value) }
    }

    private fun CurrentForecastInfo.toPresentationModel(): HomePresentationModel {
        return HomePresentationModel(
            city = this.location.name,
            dayOfMonth = this.location.localtime,
            temperatureInCelsius = "${this.currentWeatherInfo.temperatureInCelsius}°C",
            temperatureInFahrenheit = "${this.currentWeatherInfo.temperatureInFahrenheit}°F",
            weatherCondition = this.currentWeatherInfo.condition.text,
            weatherIconUrl = this.currentWeatherInfo.condition.icon
        )
    }
}