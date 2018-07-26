package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherEntity


interface WeatherCache {

    fun clearCurrentWeather(): Completable

    fun saveCurrentWeather(): Completable



    fun getCurrentWeather(): Observable<WeatherEntity>


    fun isWeatherCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isWeatherCacheExpired(): Single<Boolean>
}