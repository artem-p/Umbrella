package ru.artyompugachev.domain.model


/**
 * Weather data at the period of time
 * Contains current weather and forecasts
 * */
class Weather(val current: WeatherRecord, val forecasts: List<WeatherRecord>)