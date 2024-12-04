package com.cmaina.weatherapp.ui.screens.home

import com.cmaina.weatherapp.data.local.mapper.CurrentForecastResponseToCurrentForecastInfo
import com.cmaina.weatherapp.data.network.models.Condition
import com.cmaina.weatherapp.data.network.models.CurrentForecastInfoResponse
import com.cmaina.weatherapp.domain.models.CurrentWeatherInfo
import com.cmaina.weatherapp.domain.models.Location
import com.cmaina.weatherapp.domain.repository.SettingsRepository
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    // Subject under test
    private lateinit var viewModel: HomeViewModel
    private val settingsRepository = mockk<SettingsRepository>(relaxed = true)
    private val weatherRepository = mockk<WeatherRepository>(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(
            weatherRepository = weatherRepository,
            settingsRepository = settingsRepository
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchCurrentWeatherInfo successfully updates uiState with weather info`() = runTest {
        //Given
        val mockForecastInfo = CurrentForecastInfoResponse(
            location = Location(
                name = "Nairobi",
                localtime = "2024-12-04",
                country = "Kenya",
                region = "Nairobi Area"
            ),
            currentWeatherInfo = CurrentWeatherInfo(
                temperatureInCelsius = 25.0,
                temperatureInFahrenheit = 77.0,
                condition = Condition(text = "Sunny", icon = "sunny_icon_url"),
                windSpeedInKilometersPerHour = 20.0,
                humidity = 20,
            )
        )
        coEvery { weatherRepository.fetchCurrentWeatherInfo() } returns flowOf(
            Result.success(
                CurrentForecastResponseToCurrentForecastInfo.map(mockForecastInfo)
            )
        )

        // When
        viewModel.fetchCurrentWeatherInfo()

        advanceUntilIdle()

        // Then
        Truth.assertThat(viewModel.uiState.value.presentationModel?.city).isEqualTo("Nairobi")
    }

    @Before
    fun teardown() {
        unmockkAll()
    }
}