package com.cmaina.weatherapp.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cmaina.weatherapp.R
import com.cmaina.weatherapp.utils.formatDateTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    date: String,
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClicked: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchForecastInfo(date)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.clickable { onBackClicked() },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.arrow_back)
            )
            Text(text = stringResource(R.string.back))

            Spacer(modifier = Modifier.weight(1f))


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            uiState.data?.date?.let { date ->
                Text(
                    modifier = Modifier,
                    text = formatDateTime(date, outputPattern = "EEEE, d MMMM"),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (uiState.data?.isFromCache == true) {
                Card(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp),
                    shape = RoundedCornerShape(10)
                ) {
                    Text(text = stringResource(R.string.from_cache))
                }
            }

        }

        uiState.data?.hour?.let { hourlyWeatherConditions ->
            val useCelsius = uiState.shouldUseCelsius
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(hourlyWeatherConditions) { hourlyWeatherCondition ->
                    HourWeatherItem(
                        hourlyWeatherCondition = hourlyWeatherCondition,
                        useCelsius = useCelsius
                    )
                }
            }
        }
    }
}