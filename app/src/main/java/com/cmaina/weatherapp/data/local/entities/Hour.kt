package com.cmaina.weatherapp.data.local.entities

import com.cmaina.weatherapp.data.network.models.Condition

data class Hour(
    val temperatureInCelsius: Double,
    val temperatureInFahrenheit: Double,
    val windSpeedInKilometersPerHour: Double,
    val condition: Condition,
    val humidity: Int,
    val time: String,
)