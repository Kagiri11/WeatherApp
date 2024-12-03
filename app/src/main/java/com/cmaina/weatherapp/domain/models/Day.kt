package com.cmaina.weatherapp.domain.models

import kotlinx.serialization.Serializable

/**
 * Contains information on how the day's weather was
 */
@Serializable
data class Day(
    val condition: Condition
)