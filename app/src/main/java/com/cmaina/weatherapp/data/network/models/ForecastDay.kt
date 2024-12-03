package com.cmaina.weatherapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)