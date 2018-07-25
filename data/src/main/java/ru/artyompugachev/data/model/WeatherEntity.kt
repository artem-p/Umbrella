package ru.artyompugachev.data.model

class WeatherEntity(val time: Int,
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