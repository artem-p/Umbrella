package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherEntity


/**
 * Cache current weather and forecasts.
 * Save both at the same time.
 * */
interface WeatherCache {

    fun clearWeather(): Completable

    fun saveWeather(currentWeather: WeatherEntity, forecasts: List<WeatherEntity>): Completable

    fun getCurrentWeather(): Observable<WeatherEntity>

    fun getForecasts(): Observable<List<WeatherEntity>>

    fun isWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isWeatherCacheExpired(): Single<Boolean>
}