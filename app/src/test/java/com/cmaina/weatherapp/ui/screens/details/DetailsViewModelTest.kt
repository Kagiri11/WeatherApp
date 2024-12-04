package com.cmaina.weatherapp.ui.screens.details

import com.cmaina.weatherapp.domain.models.Condition
import com.cmaina.weatherapp.domain.models.Day
import com.cmaina.weatherapp.domain.models.ForecastDay
import com.cmaina.weatherapp.domain.repository.SettingsRepository
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {

    //Subject Under Test
    private lateinit var viewModel: DetailsViewModel
    private val weatherRepository = mockk<WeatherRepository>(relaxed = true)
    private val settingsRepository = mockk<SettingsRepository>(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailsViewModel(weatherRepository, settingsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchForecastInfo updates uiState with forecast data on success`() = runTest {
        // Given
        val mockDate = "2024-12-01"
        val mockForecastDay = ForecastDay(
            day = Day(condition = Condition(icon = "sunny_icon_url", text = "Sunny")),
            date = mockDate,
            hour = emptyList(),
            isFromCache = false
        )
        coEvery { weatherRepository.fetchWeatherInfoForDay(mockDate) } returns flowOf(
            Result.success(
                mockForecastDay
            )
        )

        // When
        viewModel.fetchForecastInfo(mockDate)

        advanceUntilIdle()

        // Then
        Truth.assertThat(viewModel.uiState.value.data?.date).isEqualTo("2024-12-01")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchForecastInfo updates uiState with error on failure`() = runTest {
        // Given
        val mockDate = "2024-12-01"
        val mockError = Exception("Network error")
        coEvery { weatherRepository.fetchWeatherInfoForDay(mockDate) } returns flowOf(
            Result.failure(
                mockError
            )
        )

        // When
        viewModel.fetchForecastInfo(mockDate)

        advanceUntilIdle()

        // Then
        Truth.assertThat(viewModel.uiState.value.errorMessage).isEqualTo("Network error")
    }
}