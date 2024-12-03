package com.cmaina.weatherapp.data.network

import android.util.Log
import com.cmaina.weatherapp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val httpClient: HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(
            Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(HttpTimeout) {
        requestTimeoutMillis = Constants.TIME_OUT
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d(
                "HTTP response =>",
                "Code: ${response.status.value}, Body: ${response.body<Any>()}"
            )
        }
    }

    install(Logging)
}