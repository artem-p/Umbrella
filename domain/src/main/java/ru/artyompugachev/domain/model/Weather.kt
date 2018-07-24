package ru.artyompugachev.domain.model

/**
 * Weather at the period of time.
 * We use this class for represent current weather as well as forecast.
 * The only difference is that current weather has only one temperature and forecast has min and max temperature.
 *
 * */
class Weather(val time: Int,
              val temperature: Double,
              val minTemperature: Double,
              val maxTemperature: Double,
              val feelsLikeTemperature: Double,
              val humidity: Double,
              val pressure: Double,
              val dewPoint: Double,
              val windSpeed: Double,
              val windDirection: Double,
              val weatherCode: Int
              )