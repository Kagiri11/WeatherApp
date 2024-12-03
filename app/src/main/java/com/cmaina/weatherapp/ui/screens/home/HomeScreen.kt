package com.cmaina.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onDateSelected: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val calendarState = rememberSelectableCalendarState()

    LaunchedEffect(calendarState.selectionState.selection) {
        if (calendarState.selectionState.selection.isNotEmpty()) {
            onDateSelected(calendarState.selectionState.selection.first().toString())
            calendarState.selectionState.selection = emptyList()
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            SelectableCalendar(modifier = Modifier.weight(1f), calendarState = calendarState)
            uiState.presentationModel?.let { WeatherConditions(modifier = Modifier.weight(1f), data = it) }
        }
    }
}

@Composable
fun WeatherConditions(
    modifier: Modifier,
    data: HomePresentationModel
) {
    Column(modifier = modifier) {
        Text(
            text = data.city,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp)
        )
        Text(
            text = data.dayOfMonth,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Text(
                    text = data.temperature,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 70.sp)
                )
                Text(text = data.weatherCondition)
            }

            Spacer(Modifier.weight(0.2f))

            AsyncImage(
                model = "https:${data.weatherIconUrl}",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4f),
                contentDescription = ""
            )
        }
    }
}