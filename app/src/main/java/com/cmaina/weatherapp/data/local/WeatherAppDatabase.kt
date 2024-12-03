package com.cmaina.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmaina.weatherapp.data.local.converters.HoursConverter
import com.cmaina.weatherapp.data.local.dao.DailyForecastDao
import com.cmaina.weatherapp.data.local.entities.ForecastDayEntity

@Database(
    entities = [ForecastDayEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(HoursConverter::class)
abstract class WeatherAppDatabase : RoomDatabase() {
    abstract fun dailyForecastDao(): DailyForecastDao
}