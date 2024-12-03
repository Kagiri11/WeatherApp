package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastInfo(
    val location: Location,
    val forecast: Forecast
)