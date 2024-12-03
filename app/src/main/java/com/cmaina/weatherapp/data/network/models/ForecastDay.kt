package com.cmaina.weatherapp.data.network.models

import com.cmaina.weatherapp.domain.models.Day
import com.cmaina.weatherapp.domain.models.Hour
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)