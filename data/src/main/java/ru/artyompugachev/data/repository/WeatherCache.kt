package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity


/**
 * Cache current weather and forecasts.
 * Save both at the same time.
 * */
interface WeatherCache {

    fun clearWeather(): Completable

    fun saveWeather(weather: WeatherEntity): Completable

    fun getWeather(): Observable<WeatherEntity>

    fun isWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isExpired(): Single<Boolean>
}