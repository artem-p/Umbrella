package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherEntity


interface WeatherCache {

    fun clearWeather(): Completable

    fun saveWeather(): Completable

    fun getCurrentWeather(): Observable<WeatherEntity>

    fun getForecasts(): Observable<List<WeatherEntity>>

    fun isWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isWeatherCacheExpired(): Single<Boolean>
}