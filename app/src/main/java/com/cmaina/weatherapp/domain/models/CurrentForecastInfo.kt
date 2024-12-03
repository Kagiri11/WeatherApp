package com.cmaina.weatherapp.domain.models

import com.cmaina.weatherapp.data.network.models.CurrentWeatherInfo
import com.cmaina.weatherapp.data.network.models.Location
import kotlinx.serialization.SerialName

data class CurrentForecastInfo(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val currentWeatherInfo: CurrentWeatherInfo
)