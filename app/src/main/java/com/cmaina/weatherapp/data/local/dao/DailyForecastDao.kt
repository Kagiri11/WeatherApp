package com.cmaina.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmaina.weatherapp.data.local.entities.ForecastDayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyForecastDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDayForecast(dailyForecast: ForecastDayEntity)

    @Query(
        value = """
        SELECT * FROM forecast_day
        WHERE date = :date
    """
    )
    fun getDayForecast(date: String): Flow<ForecastDayEntity>
}