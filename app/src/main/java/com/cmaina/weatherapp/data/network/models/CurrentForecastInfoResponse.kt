package com.cmaina.weatherapp.data.network.models

import com.cmaina.weatherapp.domain.models.CurrentWeatherInfo
import com.cmaina.weatherapp.domain.models.Location
import kotlinx.serialization.SerialName

data class CurrentForecastInfoResponse(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val currentWeatherInfo: CurrentWeatherInfo
)