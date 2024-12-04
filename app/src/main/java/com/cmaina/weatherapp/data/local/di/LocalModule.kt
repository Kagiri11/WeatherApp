package com.cmaina.weatherapp.data.local.di

import androidx.room.Room
import com.cmaina.weatherapp.data.local.WeatherAppDatabase
import com.cmaina.weatherapp.data.local.utils.DatabasePassphrase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.UUID

val localModule = module {

    single { DatabasePassphrase(context = get()) }
    /**
     * Provides [WeatherAppDatabase] instance
     */
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherAppDatabase::class.java,
            "weather_app_database"
        )
            .openHelperFactory(factory = SupportFactory(get<DatabasePassphrase>().getPassphrase()))
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides [DailyForecastDao] instance
     */
    single { get<WeatherAppDatabase>().dailyForecastDao() }
}