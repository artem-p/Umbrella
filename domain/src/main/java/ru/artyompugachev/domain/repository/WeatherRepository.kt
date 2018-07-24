package ru.artyompugachev.domain.repository

import io.reactivex.Observable
import ru.artyompugachev.domain.model.Weather

interface WeatherRepository {

    fun getCurrentWeather(): Observable<Weather>

    fun getForecasts(): Observable<List<Weather>>
}