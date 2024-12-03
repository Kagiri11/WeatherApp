package com.cmaina.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun shouldShowCelsius(): Flow<Boolean>
    suspend fun setTemperatureUnit(isCelsius: Boolean)
}