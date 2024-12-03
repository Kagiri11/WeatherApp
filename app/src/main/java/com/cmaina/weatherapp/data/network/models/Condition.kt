package com.cmaina.weatherapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val icon: String,
    val text: String
)