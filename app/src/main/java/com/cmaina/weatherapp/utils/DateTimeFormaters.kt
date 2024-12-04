package com.cmaina.weatherapp.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDateTime(
    inputDate: String,
    outputPattern: String,
    inputPattern: String = "yyyy-MM-dd"
): String {
    val inputFormatter = DateTimeFormatter.ofPattern(inputPattern, Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern(outputPattern, Locale.getDefault())

    val date = LocalDate.parse(inputDate, inputFormatter)
    return date.format(outputFormatter)
}

fun formatTimeToAmOrPm(inputDateTime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

    val dateTime = LocalDateTime.parse(inputDateTime, inputFormatter)
    return dateTime.format(outputFormatter)
}