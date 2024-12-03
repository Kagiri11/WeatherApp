package com.cmaina.weatherapp.domain.repository

import com.cmaina.weatherapp.domain.models.CurrentForecastInfo
import com.cmaina.weatherapp.domain.models.ForecastInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchWeatherInfoForDay(date: String): Flow<Result<ForecastInfo>>
    suspend fun fetchCurrentWeatherInfo(): Flow<Result<CurrentForecastInfo>>
}