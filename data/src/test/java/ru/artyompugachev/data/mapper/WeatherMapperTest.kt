package ru.artyompugachev.data.mapper

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.artyompugachev.data.factory.WeatherDataFactory
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.domain.model.Weather
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class WeatherMapperTest {

    private val mapper = WeatherMapper()

    @Test
    fun mapFromEntity() {
        val entity = WeatherDataFactory.makeWeatherEntity()
        val domain = mapper.mapFromEntity(entity)

        assertEqualData(entity, domain)
    }


    @Test
    fun mapToEntity() {
        val domain = WeatherDataFactory.makeWeather()
        val entity = mapper.mapToEntity(domain)

        assertEqualData(entity, domain)
    }


    private fun assertEqualData(entity: WeatherEntity,
                                domain: Weather) {

        assertEquals(entity.current.time, domain.current.time)

    }
}