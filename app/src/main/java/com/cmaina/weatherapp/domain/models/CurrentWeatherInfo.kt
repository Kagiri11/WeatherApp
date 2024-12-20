package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains current weather forecast information
 */
@Serializable
data class CurrentWeatherInfo(
    @SerialName("wind_kph")
    val windSpeedInKilometersPerHour: Double,
    @SerialName("temp_c")
    val temperatureInCelsius: Double,
    @SerialName("temp_f")
    val temperatureInFahrenheit: Double,
    val humidity: Int,
    val condition: Condition
)
