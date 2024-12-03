package com.cmaina.weatherapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "forecast_day")
@Serializable
data class ForecastDay(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val day: Day,
    val hour: List<Hour>,
    val isFromCache: Boolean = false
)