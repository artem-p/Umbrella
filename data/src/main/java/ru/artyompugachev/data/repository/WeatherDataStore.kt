package ru.artyompugachev.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherRecordEntity

interface WeatherDataStore {

    fun getCurrentWeather(): Observable<WeatherRecordEntity>

    fun getForecasts(): Observable<List<WeatherRecordEntity>>

    fun saveWeather(currentWeatherRecord: WeatherRecordEntity, forecasts: List<WeatherRecordEntity>): Completable

    fun clearWeather(): Completable
}