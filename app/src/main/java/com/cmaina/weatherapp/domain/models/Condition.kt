package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    val icon: String,
    val text: String
)