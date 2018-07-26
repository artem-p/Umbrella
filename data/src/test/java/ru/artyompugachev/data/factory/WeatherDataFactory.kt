package ru.artyompugachev.data.factory

import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity


object WeatherDataFactory {

    private fun randomDouble(): Double {
        return Math.random()
    }


    private fun randomInt(): Int {
        return Math.random().toInt()
    }


    private fun makeWeatherRecordEntity(): WeatherRecordEntity {
        return WeatherRecordEntity (randomInt(), randomDouble(), randomDouble(), randomDouble(), randomDouble(),
                randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomDouble(), randomInt())
    }


    private fun makeWeatherEntityList(number: Int): List<WeatherRecordEntity> {
        val weathers = mutableListOf<WeatherRecordEntity>()

        repeat(number) {
            weathers.add(makeWeatherRecordEntity())
        }

        return weathers
    }

    fun makeWeatherEntity(): WeatherEntity {
        return WeatherEntity(makeWeatherRecordEntity(), makeWeatherEntityList(3))
    }
}