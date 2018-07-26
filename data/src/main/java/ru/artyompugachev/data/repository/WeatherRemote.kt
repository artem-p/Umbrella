package ru.artyompugachev.data.repository

import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.domain.model.Weather


interface WeatherRemote {

    fun getCurrentWeather(): Observable<WeatherEntity>

    fun getForecasts(): Observable<List<WeatherEntity>>
}