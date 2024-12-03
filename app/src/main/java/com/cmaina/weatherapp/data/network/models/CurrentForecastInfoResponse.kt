package com.cmaina.weatherapp.data.network.models

import kotlinx.serialization.SerialName

data class CurrentForecastInfoResponse(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val currentWeatherInfo: CurrentWeatherInfo
)