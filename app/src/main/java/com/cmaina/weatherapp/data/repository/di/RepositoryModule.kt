package com.cmaina.weatherapp.data.repository.di

import com.cmaina.weatherapp.data.repository.WeatherRepositoryImpl
import com.cmaina.weatherapp.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(weatherApi = get(), weatherDao = get())
    }
}