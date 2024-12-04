package com.cmaina.weatherapp.data.network.api

import com.cmaina.weatherapp.data.network.models.CurrentForecastInfoResponse
import com.cmaina.weatherapp.data.network.models.ForecastInfoResponse
import com.cmaina.weatherapp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class WeatherApiImpl(private val httpClient: HttpClient) : WeatherApi {

    override suspend fun fetchCurrentWeatherInfo(): Result<CurrentForecastInfoResponse> {
        try {
            val response = httpClient.get(Constants.CURRENT_WEATHER_ENDPOINT) {
                url {
                    parameters.append("key", Constants.API_KEY)
                    parameters.append("q", "Nairobi")
                }
            }

            if (response.status.value == 200) {
                val currentWeatherInfo: CurrentForecastInfoResponse = response.body()
                return Result.success(currentWeatherInfo)
            } else {
                val error = response.body<String>()
                val formattedErrorMessage = error.asFormattedErrorMessage()
                return Result.failure(Exception(formattedErrorMessage))
            }

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun fetchHistoricalWeatherInfo(date: String): Result<ForecastInfoResponse> {
        try {
            val response = httpClient.get(Constants.WEATHER_HISTORY_ENDPOINT) {
                url {
                    parameters.append("key", Constants.API_KEY)
                    parameters.append("q", "Nairobi")
                    parameters.append("dt", date)
                }
            }

            if (response.status.value == 200) {
                val currentWeatherInfo: ForecastInfoResponse = response.body()
                return Result.success(currentWeatherInfo)
            } else {
                val error = response.body<String>()
                val formattedErrorMessage = error.asFormattedErrorMessage()
                return Result.failure(Exception(formattedErrorMessage))
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun String.asFormattedErrorMessage(): String{
        return this.substringAfterLast("message\":\"").substringBefore("\"}")
    }
}