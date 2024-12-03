package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.SerialName

data class CurrentForecastInfo(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val currentWeatherInfo: CurrentWeatherInfo
)