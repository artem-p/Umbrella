package ru.artyompugachev.data.mapper

import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.domain.model.Weather
import javax.inject.Inject

open class WeatherMapper @Inject constructor() : EntityMapper<WeatherEntity, Weather> {
    override fun mapFromEntity(entity: WeatherEntity): Weather {
        val weatherRecordMapper = WeatherRecordMapper()

        return Weather(weatherRecordMapper.mapFromEntity(entity.current), entity.forecasts.map { weatherRecordMapper.mapFromEntity(it) })
    }

    override fun mapToEntity(domain: Weather): WeatherEntity {
        val weatherRecordMapper = WeatherRecordMapper()

        return WeatherEntity(weatherRecordMapper.mapToEntity(domain.current), domain.forecasts.map { weatherRecordMapper.mapToEntity(it) })
    }
}