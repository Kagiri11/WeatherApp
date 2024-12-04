package com.cmaina.weatherapp.data.repository

import com.cmaina.weatherapp.data.local.dao.WeatherDao
import com.cmaina.weatherapp.data.local.mapper.CurrentForecastResponseToCurrentForecastInfo
import com.cmaina.weatherapp.data.network.api.WeatherApi
import com.cmaina.weatherapp.domain.models.CurrentForecastInfo
import com.cmaina.weatherapp.domain.models.ForecastDay
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override suspend fun fetchWeatherInfoForDay(date: String): Flow<Result<ForecastDay>> {
        val cachedData = weatherDao.getDayForecast(date).firstOrNull()
        return if (cachedData != null) {
            flowOf(Result.success(cachedData))
        } else {
            flowOf(
                weatherApi.fetchHistoricalWeatherInfo(date = date).map { response ->
                    response.forecast.dailyForecasts.first()
                }
            )
        }
    }

    override suspend fun fetchCurrentWeatherInfo(): Flow<Result<CurrentForecastInfo>> {
        return flowOf(
            weatherApi.fetchCurrentWeatherInfo().map { response ->
                CurrentForecastResponseToCurrentForecastInfo.map(response)
            }
        )
    }

    override suspend fun saveWeatherInfo(forecastDay: ForecastDay) {
        weatherDao.insertDayForecast(forecastDay)
    }
}