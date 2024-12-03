package com.cmaina.weatherapp.data.local.converters

import androidx.room.TypeConverter
import com.cmaina.weatherapp.data.local.entities.Hour
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class HoursConverter {

    private val gson = Gson()
    private val type: Type = object : TypeToken<List<Hour>>() {}.type

    @TypeConverter
    fun toList(convertedString: String?): List<Hour>? {
        return if (convertedString.isNullOrEmpty()) null
        else gson.fromJson(convertedString, type)
    }

    @TypeConverter
    fun toString(hours: List<Hour>): String? {
        return if (hours.isEmpty()) null
        else gson.toJson(hours, type)
    }

}