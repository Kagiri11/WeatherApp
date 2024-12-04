package com.cmaina.weatherapp.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cmaina.weatherapp.domain.models.Hour
import com.cmaina.weatherapp.utils.formatDateTime
import com.cmaina.weatherapp.utils.formatTimeToAmOrPm

@Composable
fun HourWeatherItem(
    useCelsius: Boolean = true,
    hourlyWeatherCondition: Hour
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTimeToAmOrPm(inputDateTime = hourlyWeatherCondition.time),
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxHeight(0.5f),
                    model = "https:${hourlyWeatherCondition.condition.icon}",
                    contentDescription = ""
                )
                Spacer(Modifier.width(5.dp))
                Text(hourlyWeatherCondition.condition.text)
            }

            val temperature = if (useCelsius)
                "${hourlyWeatherCondition.temperatureInCelsius}°C" else
                "${hourlyWeatherCondition.temperatureInFahrenheit}°F"

            Text(
                modifier = Modifier.weight(1f),
                text = temperature,
                style = MaterialTheme.typography.titleLarge.copy(
                    textAlign = TextAlign.End
                )
            )
        }
    }
}