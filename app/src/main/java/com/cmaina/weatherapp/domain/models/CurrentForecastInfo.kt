package com.cmaina.weatherapp.domain.models

data class CurrentForecastInfo(
    val location: Location,
    val currentWeatherInfo: CurrentWeatherInfo
)