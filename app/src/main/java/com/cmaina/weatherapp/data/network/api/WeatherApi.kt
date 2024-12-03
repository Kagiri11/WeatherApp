package com.cmaina.weatherapp.data.network.api

import com.cmaina.weatherapp.data.network.models.CurrentForecastInfoResponse
import com.cmaina.weatherapp.data.network.models.ForecastInfoResponse

interface WeatherApi {

    suspend fun fetchCurrentWeatherInfo(): Result<CurrentForecastInfoResponse>

    suspend fun fetchHistoricalWeatherInfo(): Result<ForecastInfoResponse>

}