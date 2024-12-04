package com.cmaina.weatherapp.data.repository

import com.cmaina.weatherapp.data.local.utils.SettingsManager
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class SettingsRepositoryImplTest {

    private lateinit var settingsManager: SettingsManager
    private lateinit var settingsRepositoryImpl: SettingsRepositoryImpl

    @Before
    fun setUp() {
        settingsManager = mockk(relaxed = true)
        settingsRepositoryImpl = SettingsRepositoryImpl(settingsManager = settingsManager)
    }

    @Test
    fun `shouldShowCelsius returns true when preference is set to Celsius`() = runTest {
        coEvery { settingsManager.getTemperatureUnit() } returns flowOf(true)

        val result = settingsRepositoryImpl.shouldShowCelsius()

        assertThat(result.first()).isEqualTo(true)
    }

    @Test
    fun `shouldShowCelsius returns false when preference is set to Fahrenheit`() = runTest {
        coEvery { settingsManager.getTemperatureUnit() } returns flowOf(false)

        val result = settingsRepositoryImpl.shouldShowCelsius()

        assertThat(result.first()).isEqualTo(false)
    }

    @Test
    fun `setTemperatureUnit calls the setTemperatureUnit in SettingsManager with the correct preference`() = runTest {

        settingsRepositoryImpl.setTemperatureUnit(true)

        coVerify { settingsManager.setTemperatureUnit(true) }
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}