package ru.artyompugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.repository.WeatherDataStore
import javax.inject.Inject

open class WeatherCacheDataStore @Inject constructor(private val weatherCache: WeatherCache):
        WeatherDataStore{

    override fun getWeather(): Observable<WeatherEntity> {
        return weatherCache.getWeather()
    }


    override fun saveWeather(weather: WeatherEntity): Completable {
        return weatherCache.saveWeather(weather)
                .andThen(weatherCache.setLastCacheTime(System.currentTimeMillis()))
    }


    override fun clearWeather(): Completable {
        return weatherCache.clearWeather()
    }
}