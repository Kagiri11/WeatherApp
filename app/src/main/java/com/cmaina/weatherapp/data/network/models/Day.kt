package com.cmaina.weatherapp.data.network.models

import com.cmaina.weatherapp.data.network.models.Condition
import kotlinx.serialization.Serializable

/**
 * Contains information on how the day's weather was
 */
@Serializable
data class Day(
    val condition: Condition
)