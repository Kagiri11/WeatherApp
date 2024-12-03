package com.cmaina.weatherapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastInfoResponse(
    val location: Location,
    val forecast: Forecast
)