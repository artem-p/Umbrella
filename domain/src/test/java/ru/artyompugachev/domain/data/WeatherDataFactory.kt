package ru.artyompugachev.domain.data

import ru.artyompugachev.domain.model.Weather

class WeatherDataFactory {

    fun randomDouble(): Double {
        return Math.random()
    }


    fun randomInt(): Int {
        return Math.random().toInt()
    }


    fun makeWeather(): Weather {
        return Weather(randomInt(), randomDouble(), randomDouble(), randomDouble(), randomDouble(),
                randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomInt())
    }


    fun makeWeatherList(number: Int): List<Weather> {
        val weathers = mutableListOf<Weather>()

        repeat(number) {
            weathers.add(makeWeather())
        }

        return weathers
    }
}