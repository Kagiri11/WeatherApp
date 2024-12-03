package com.cmaina.weatherapp.data.local.di

import androidx.room.Room
import com.cmaina.weatherapp.data.local.WeatherAppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    /**
     * Provides [WeatherAppDatabase] instance
     */
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherAppDatabase::class.java,
            "weather_app_database"
        ).build()
    }

    /**
     * Provides [DailyForecastDao] instance
     */
    single { get<WeatherAppDatabase>().dailyForecastDao() }
}