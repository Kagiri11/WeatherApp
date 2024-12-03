package com.cmaina.weatherapp

import android.app.Application
import com.cmaina.weatherapp.data.local.di.localModule
import com.cmaina.weatherapp.data.network.di.networkModule
import com.cmaina.weatherapp.data.repository.di.repositoryModule
import com.cmaina.weatherapp.ui.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(localModule, networkModule, repositoryModule, presentationModule)
        startKoin {
            androidLogger()
            androidContext(this@WeatherApp)
            modules(modules)
        }
    }
}