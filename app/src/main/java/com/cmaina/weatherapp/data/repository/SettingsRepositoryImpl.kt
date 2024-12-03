package com.cmaina.weatherapp.data.repository

import com.cmaina.weatherapp.data.local.utils.SettingsManager
import com.cmaina.weatherapp.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsManager: SettingsManager
) : SettingsRepository {

    override suspend fun shouldShowCelsius() = settingsManager.getTemperatureUnit()

    override suspend fun setTemperatureUnit(isCelsius: Boolean) {
        settingsManager.setTemperatureUnit(isCelsius)
    }
}