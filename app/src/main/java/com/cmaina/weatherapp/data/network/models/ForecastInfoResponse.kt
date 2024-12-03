package com.cmaina.weatherapp.data.network.models

import com.cmaina.weatherapp.domain.models.Forecast
import com.cmaina.weatherapp.domain.models.Location
import kotlinx.serialization.Serializable

@Serializable
data class ForecastInfoResponse(
    val location: Location,
    val forecast: Forecast
)