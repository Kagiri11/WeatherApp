package com.cmaina.weatherapp.data.repository

import com.cmaina.weatherapp.data.local.dao.WeatherDao
import com.cmaina.weatherapp.data.network.api.WeatherApi
import com.cmaina.weatherapp.data.network.models.ForecastInfoResponse
import com.cmaina.weatherapp.domain.models.Condition
import com.cmaina.weatherapp.domain.models.Day
import com.cmaina.weatherapp.domain.models.Forecast
import com.cmaina.weatherapp.domain.models.ForecastDay
import com.cmaina.weatherapp.domain.models.Location
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var weatherDao: WeatherDao
    private lateinit var weatherRepository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        weatherApi = mockk(relaxed = true)
        weatherDao = mockk(relaxed = true)
        weatherRepository = WeatherRepositoryImpl(weatherApi, weatherDao)
    }

    @Test
    fun `fetchWeatherInfoForDay returns cached data when available`() = runTest {
        val mockDate = "2024-12-01"
        val cachedForecast = ForecastDay(
            day = Day(condition = Condition(icon = "", text = "Sunny")),
            date = mockDate,
            hour = emptyList()
        )
        coEvery { weatherDao.getDayForecast(mockDate) } returns flowOf(cachedForecast)

        val result: Flow<Result<ForecastDay>> = weatherRepository.fetchWeatherInfoForDay(mockDate)

        assertThat(result.first()).isEqualTo(Result.success(cachedForecast))
        coVerify { weatherDao.getDayForecast(mockDate) }
        coVerify(exactly = 0) { weatherApi.fetchHistoricalWeatherInfo(any()) }
    }

    @Test
    fun `fetchWeatherInfoForDay fetches from API when cache is unavailable`() = runTest {
        val mockDate = "2024-12-01"
        val apiResponse = ForecastInfoResponse(
            forecast = Forecast(
                dailyForecasts = listOf(
                    ForecastDay(
                        day = Day(condition = Condition(icon = "", text = "Sunny")),
                        date = mockDate,
                        hour = emptyList()
                    )
                )
            ),
            location = Location(
                name = "Nairobi",
                country = "Kenya",
                localtime = "",
                region = "Nairobi Area"
            )
        )
        coEvery { weatherDao.getDayForecast(mockDate) } returns emptyFlow()
        coEvery { weatherApi.fetchHistoricalWeatherInfo(mockDate) } returns Result.success(
            apiResponse
        )

        val result = weatherRepository.fetchWeatherInfoForDay(mockDate)

        assertThat(result.first().isSuccess).isEqualTo(true)
        coVerify { weatherDao.getDayForecast(mockDate) }
        coVerify { weatherApi.fetchHistoricalWeatherInfo(mockDate) }
    }

    @Test
    fun `saveWeatherInfo calls insertDayForecast on WeatherDao`() = runTest {
        val mockDate = "2024-12-01"
        val forecastDay = ForecastDay(
            day = Day(condition = Condition(icon = "", text = "Sunny")),
            date = mockDate,
            hour = emptyList()
        )

        weatherRepository.saveWeatherInfo(forecastDay)

        coVerify { weatherDao.insertDayForecast(forecastDay) }
    }
}