package ru.artyompugachev.data.mapper

import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.domain.model.WeatherRecord
import javax.inject.Inject


class WeatherMapper @Inject constructor() : EntityMapper<WeatherEntity, WeatherRecord> {
    override fun mapFromEntity(entity: WeatherEntity): WeatherRecord {
        return WeatherRecord(entity.time, entity.temperature, entity.minTemperature, entity.maxTemperature,
                entity.feelsLikeTemperature, entity.humidity, entity.pressure, entity.dewPoint,
                entity.windSpeed, entity.windDirection, entity.weatherCode)
    }

    override fun mapToEntity(domain: WeatherRecord): WeatherEntity {
        return WeatherEntity(domain.time, domain.temperature, domain.minTemperature, domain.maxTemperature,
                domain.feelsLikeTemperature, domain.humidity, domain.pressure, domain.dewPoint,
                domain.windSpeed, domain.windDirection, domain.weatherCode)
    }
}