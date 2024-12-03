package com.cmaina.weatherapp.data.network.models

import kotlinx.serialization.Serializable

/**
 * Contains geographical location information
 */
@Serializable
data class Location(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String
)