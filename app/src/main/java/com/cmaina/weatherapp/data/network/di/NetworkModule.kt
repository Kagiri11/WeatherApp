package com.cmaina.weatherapp.data.network.di

import com.cmaina.weatherapp.data.network.api.WeatherApi
import com.cmaina.weatherapp.data.network.api.WeatherApiImpl
import com.cmaina.weatherapp.data.network.httpClient
import org.koin.dsl.module

val networkModule = module {
    single { httpClient }

    factory<WeatherApi> { WeatherApiImpl(httpClient = get()) }
}