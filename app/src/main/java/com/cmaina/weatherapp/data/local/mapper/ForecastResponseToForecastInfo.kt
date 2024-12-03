package com.cmaina.weatherapp.data.local.mapper

import com.cmaina.weatherapp.data.network.models.ForecastInfoResponse
import com.cmaina.weatherapp.domain.models.ForecastInfo

object ForecastResponseToForecastInfo : Mapper<ForecastInfoResponse, ForecastInfo> {
    override fun map(from: ForecastInfoResponse): ForecastInfo {
        return ForecastInfo(
            location = from.location,
            forecast = from.forecast
        )
    }
}