package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity

interface WeatherDataStore {

    fun getCurrentWeather(): Observable<WeatherEntity>

    fun getForecasts(): Observable<List<WeatherEntity>>

    fun saveWeather(currentWeather: WeatherEntity, forecasts: List<WeatherEntity>): Completable

    fun clearWeather(): Completable
}