package com.cmaina.weatherapp.ui.screens.home

data class HomePresentationModel(
    val city: String,
    val dayOfMonth: String,
    val temperature: String,
    val weatherCondition: String,
    val weatherIconUrl: String
)
