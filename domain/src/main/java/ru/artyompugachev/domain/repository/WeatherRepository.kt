package ru.artyompugachev.domain.repository

import io.reactivex.Observable
import ru.artyompugachev.domain.model.Weather
import ru.artyompugachev.domain.model.WeatherRecord

interface WeatherRepository {

    fun getWeather(): Observable<Weather>
}