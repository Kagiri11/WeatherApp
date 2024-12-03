package com.cmaina.weatherapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmaina.weatherapp.domain.models.Day
import com.cmaina.weatherapp.domain.models.Hour

@Entity(tableName = "forecast_day")
data class ForecastDayEntity(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val day: Day,
    val hour: List<Hour>
)