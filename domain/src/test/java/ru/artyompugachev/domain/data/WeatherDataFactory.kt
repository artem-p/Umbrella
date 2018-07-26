package ru.artyompugachev.domain.data

import ru.artyompugachev.domain.model.Weather
import ru.artyompugachev.domain.model.WeatherRecord

object WeatherDataFactory {

    private fun randomDouble(): Double {
        return Math.random()
    }


    private fun randomInt(): Int {
        return Math.random().toInt()
    }


    private fun makeWeatherRecord(): WeatherRecord {
        return WeatherRecord(randomInt(), randomDouble(), randomDouble(), randomDouble(), randomDouble(),
                randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomInt())
    }


    private fun makeWeatherList(number: Int): List<WeatherRecord> {
        val weathers = mutableListOf<WeatherRecord>()

        repeat(number) {
            weathers.add(makeWeatherRecord())
        }

        return weathers
    }

    fun makeWeather(): Weather {
        return Weather(makeWeatherRecord(), makeWeatherList(3))
    }
}