package com.cmaina.weatherapp.domain.models

import com.cmaina.weatherapp.data.network.models.ForecastDay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("forecastday")
    val dailyForecasts: List<ForecastDay>
)