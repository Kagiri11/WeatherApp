package com.cmaina.weatherapp.data.local.converters

import androidx.room.TypeConverter
import com.cmaina.weatherapp.domain.models.Day
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DayConverter {
    private val gson = Gson()
    private val type: Type = object : TypeToken<Day>() {}.type

    @TypeConverter
    fun toList(convertedString: String?): Day? {
        return if (convertedString.isNullOrEmpty()) null
        else gson.fromJson(convertedString, type)
    }

    @TypeConverter
    fun toString(day: Day): String? {
        return gson.toJson(day, type)
    }
}