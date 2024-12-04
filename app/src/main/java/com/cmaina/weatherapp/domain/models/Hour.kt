package com.cmaina.weatherapp.domain.models

import com.cmaina.weatherapp.data.network.models.Condition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains hour by hour weather forecast information
 */
@Serializable
data class Hour(
    @SerialName("temp_c")
    val temperatureInCelsius: Double,
    @SerialName("temp_f")
    val temperatureInFahrenheit: Double,
    @SerialName("wind_kph")
    val windSpeedInKilometersPerHour: Double,
    val condition: Condition,
    val humidity: Int,
    val time: String,
)