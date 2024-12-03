package com.cmaina.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cmaina.weatherapp.ui.screens.details.DetailsScreen
import com.cmaina.weatherapp.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Details(val date: String)

@Composable
fun WeatherAppNavHost(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(modifier = modifier, navController = navController, startDestination = Home) {

        composable<Home> {
            HomeScreen(
                onDateSelected = { selectedDate ->
                    navController.navigate(route = Details(date = selectedDate))
                }
            )
        }

        composable<Details> { backStackEntry ->
            val details: Details = backStackEntry.toRoute()
            DetailsScreen(date = details.date)
        }

    }
}