package com.cmaina.weatherapp.data.local.entities

import androidx.room.Entity
import com.cmaina.weatherapp.data.network.models.Day
import com.cmaina.weatherapp.data.network.models.Hour

@Entity(tableName = "forecast_day")
data class ForecastDayEntity(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)