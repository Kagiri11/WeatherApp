package com.cmaina.weatherapp.data.local.mapper

interface Mapper<in From, out To> {
    fun map(from: From): To
}