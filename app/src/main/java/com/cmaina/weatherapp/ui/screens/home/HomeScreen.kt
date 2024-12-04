package com.cmaina.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.cmaina.weatherapp.R
import com.cmaina.weatherapp.utils.formatDateTime
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.select_a_date_to_view_weather_conditions),
                )
                Spacer(Modifier.weight(1f))
                TemperatureSwitch(
                    initialSwitchState = uiState.shouldUseCelsius,
                    onCheckedChange = {
                        viewModel.changeTemperatureUnit(it)
                    }
                )
            }
            Spacer(Modifier.height(10.dp))
            SelectableCalendar(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(horizontal = 10.dp, vertical = 5.dp), calendarState = calendarState,
                monthHeader = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it.currentMonth.month.name,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                        textAlign = TextAlign.Center
                    )
                }
            )
            uiState.presentationModel?.let {
                WeatherConditions(
                    modifier = Modifier.weight(0.4f),
                    data = it,
                    useCelsius = uiState.shouldUseCelsius
                )
            }
        }
    }
}

@Composable
fun WeatherConditions(
    modifier: Modifier,
    data: HomePresentationModel,
    useCelsius: Boolean = true,
) {
    Column(modifier = modifier) {
        Text(
            text = data.city,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp)
        )
        Text(
            text = formatDateTime(
                data.dayOfMonth.substringBefore(" "),
                outputPattern = "EEEE, d MMMM"
            ),
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
                modifier = Modifier.weight(0.6f)
            ) {
                Text(
                    text = if (useCelsius) data.temperatureInCelsius else data.temperatureInFahrenheit,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 60.sp)
                )
                Text(text = data.weatherCondition)
            }

            Spacer(Modifier.weight(0.1f))

            AsyncImage(
                model = "https:${data.weatherIconUrl}",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.3f),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun TemperatureSwitch(initialSwitchState: Boolean, onCheckedChange: (Boolean) -> Unit) {
    var checked by remember { mutableStateOf(initialSwitchState) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = if (checked) stringResource(R.string.celsius) else stringResource(R.string.fahrenheit))
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                onCheckedChange(it)
            }
        )
    }
}