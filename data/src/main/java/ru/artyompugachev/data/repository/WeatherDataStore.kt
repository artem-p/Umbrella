package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity

interface WeatherDataStore {

    fun getWeather(): Observable<WeatherEntity>

    fun saveWeather(weather: WeatherEntity): Completable

    fun clearWeather(): Completable
}