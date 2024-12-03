package com.cmaina.weatherapp.domain.repository

import com.cmaina.weatherapp.domain.models.CurrentForecastInfo
import com.cmaina.weatherapp.domain.models.ForecastDay
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun saveWeatherInfo(forecastDay: ForecastDay)
    suspend fun fetchWeatherInfoForDay(date: String): Flow<Result<ForecastDay>>
    suspend fun fetchCurrentWeatherInfo(): Flow<Result<CurrentForecastInfo>>
}