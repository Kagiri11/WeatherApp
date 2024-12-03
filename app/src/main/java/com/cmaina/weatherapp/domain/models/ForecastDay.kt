package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)