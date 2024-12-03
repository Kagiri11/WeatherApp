package com.cmaina.weatherapp.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun HourWeatherItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("12:00")
        Row {
            AsyncImage(
                model = "https://cdn.weatherapi.com/weather/64x64/day/353.png",
                contentDescription = ""
            )
            Text("Sunny")
        }

        Text("17")
    }
}