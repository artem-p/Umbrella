package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherRecordEntity


/**
 * Cache current weather and forecasts.
 * Save both at the same time.
 * */
interface WeatherCache {

    fun clearWeather(): Completable

    fun saveWeather(currentWeatherRecord: WeatherRecordEntity, forecasts: List<WeatherRecordEntity>): Completable

    fun getCurrentWeather(): Observable<WeatherRecordEntity>

    fun getForecasts(): Observable<List<WeatherRecordEntity>>

    fun isWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isWeatherCacheExpired(): Single<Boolean>
}