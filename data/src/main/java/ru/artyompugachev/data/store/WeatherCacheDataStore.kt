package ru.artyompugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.repository.WeatherDataStore
import javax.inject.Inject

open class WeatherCacheDataStore @Inject constructor(private val weatherCache: WeatherCache):
        WeatherDataStore{
    override fun getCurrentWeather(): Observable<WeatherEntity> {
        return weatherCache.getCurrentWeather()
    }

    override fun getForecasts(): Observable<List<WeatherEntity>> {
        return weatherCache.getForecasts()
    }

    override fun saveWeather(currentWeather: WeatherEntity, forecasts: List<WeatherEntity>): Completable {
        return weatherCache.saveWeather(currentWeather, forecasts)
                .andThen(weatherCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearWeather(): Completable {
        return weatherCache.clearWeather()
    }
}