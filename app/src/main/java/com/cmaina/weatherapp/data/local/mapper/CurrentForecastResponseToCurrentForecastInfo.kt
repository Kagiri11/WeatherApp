package com.cmaina.weatherapp.data.local.mapper

import com.cmaina.weatherapp.data.network.models.CurrentForecastInfoResponse
import com.cmaina.weatherapp.domain.models.CurrentForecastInfo

object CurrentForecastResponseToCurrentForecastInfo: Mapper<CurrentForecastInfoResponse, CurrentForecastInfo> {
    override fun map(from: CurrentForecastInfoResponse): CurrentForecastInfo {
        return CurrentForecastInfo(
            location = from.location,
            currentWeatherInfo = from.currentWeatherInfo
        )
    }
}