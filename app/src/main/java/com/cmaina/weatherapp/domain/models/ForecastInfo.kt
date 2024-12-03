package com.cmaina.weatherapp.domain.models

import com.cmaina.weatherapp.data.network.models.Forecast
import com.cmaina.weatherapp.data.network.models.Location
import kotlinx.serialization.Serializable

@Serializable
data class ForecastInfo(
    val location: Location,
    val forecast: Forecast
)