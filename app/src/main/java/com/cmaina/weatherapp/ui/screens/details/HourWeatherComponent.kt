package com.cmaina.weatherapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.cmaina.weatherapp.domain.models.Hour

@Composable
fun HourWeatherItem(
    useCelsius: Boolean = true,
    hourlyWeatherCondition: Hour
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(hourlyWeatherCondition.time)
        Row {
            AsyncImage(
                model = "https:${hourlyWeatherCondition.condition.icon}",
                contentDescription = ""
            )
            Text(hourlyWeatherCondition.condition.text)
        }

        val temperature = if (useCelsius)
            hourlyWeatherCondition.temperatureInCelsius else
            hourlyWeatherCondition.temperatureInFahrenheit

        Text(text = "$temperature")
    }
}