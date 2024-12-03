package com.cmaina.weatherapp.utils

import com.cmaina.weatherapp.BuildConfig

object Constants {

    // Networking
    const val API_KEY = BuildConfig.API_KEY
    const val TIME_OUT = 6000L
    private const val BASE_URL = BuildConfig.BASE_URL
    const val CURRENT_WEATHER_ENDPOINT = "$BASE_URL/current.json"
    const val WEATHER_HISTORY_ENDPOINT = "$BASE_URL/history.json"
}