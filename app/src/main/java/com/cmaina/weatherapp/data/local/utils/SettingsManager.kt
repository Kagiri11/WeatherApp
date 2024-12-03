package com.cmaina.weatherapp.data.local.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.cmaina.weatherapp.data.local.PreferencesKeys
import com.cmaina.weatherapp.data.local.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsManager(private val context: Context) {

    // Save temperature preference
    suspend fun setTemperatureUnit(isCelsius: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_CELSIUS] = isCelsius
        }
    }

    // Read temperature preference
    fun getTemperatureUnit(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IS_CELSIUS] ?: true
        }
    }
}