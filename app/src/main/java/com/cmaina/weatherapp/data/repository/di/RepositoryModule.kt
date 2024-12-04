package com.cmaina.weatherapp.data.repository.di

import com.cmaina.weatherapp.data.local.utils.SettingsManager
import com.cmaina.weatherapp.data.repository.SettingsRepositoryImpl
import com.cmaina.weatherapp.data.repository.WeatherRepositoryImpl
import com.cmaina.weatherapp.domain.repository.SettingsRepository
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<WeatherRepository> {
        WeatherRepositoryImpl(weatherApi = get(), weatherDao = get())
    }
    factory { SettingsManager(context = get()) }
    factory<SettingsRepository> { SettingsRepositoryImpl(settingsManager = get()) }
}