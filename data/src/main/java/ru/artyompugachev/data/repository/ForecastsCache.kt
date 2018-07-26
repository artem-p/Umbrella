package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.artyompugachev.data.model.WeatherEntity

interface ForecastsCache {

    fun clearForecasts(): Completable

    fun saveForecasts(): Completable

    fun getForecasts(): Observable<List<WeatherEntity>>

    fun areForecastsCached(): Single<Boolean>

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isForecastsCacheExpired(): Single<Boolean>
}