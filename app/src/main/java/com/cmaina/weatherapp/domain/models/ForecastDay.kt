package com.cmaina.weatherapp.domain.models

import com.cmaina.weatherapp.data.network.models.Day
import com.cmaina.weatherapp.data.network.models.Hour
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)