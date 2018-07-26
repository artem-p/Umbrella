package ru.artyompugachev.data.model

class WeatherEntity(val current: WeatherRecordEntity, val forecasts: List<WeatherRecordEntity>) {
}