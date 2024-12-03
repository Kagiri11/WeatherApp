package com.cmaina.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit = {}
) {
    val calendarState = rememberSelectableCalendarState()

    LaunchedEffect(calendarState.selectionState.selection) {
        if (calendarState.selectionState.selection.isNotEmpty()) {
            onDateSelected(calendarState.selectionState.selection.first().toString())
            calendarState.selectionState.selection = emptyList()
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        SelectableCalendar(modifier = Modifier.weight(1f), calendarState = calendarState)
        WeatherConditions(modifier = Modifier.weight(1f))
    }
}

@Composable
fun WeatherConditions(modifier: Modifier) {
    Column(modifier = modifier) {
        AsyncImage(
            model = "https://cdn.weatherapi.com/weather/64x64/day/176.png",
            contentDescription = ""
        )
        Text("21°C")
        Text("Sunny")
        Text("Friday 13 - 09:41 am")
    }
}