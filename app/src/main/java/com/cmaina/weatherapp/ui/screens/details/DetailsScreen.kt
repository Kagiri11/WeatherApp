package com.cmaina.weatherapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun DetailsScreen(
    date: String
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = ""
            )
            Text(text = "Back")

            Spacer(modifier = Modifier.weight(1f))

            AsyncImage(
                model = "https://cdn.weatherapi.com/weather/64x64/day/353.png",
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
        }

        Text(text = "Sunday, 23 April")

        LazyColumn {
            items(24) {
                HourWeatherItem()
            }
        }

    }
}