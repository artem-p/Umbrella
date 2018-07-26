package ru.artyompugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherRecordEntity
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.repository.WeatherDataStore
import javax.inject.Inject

open class WeatherCacheDataStore @Inject constructor(private val weatherCache: WeatherCache):
        WeatherDataStore{
    override fun getCurrentWeather(): Observable<WeatherRecordEntity> {
        return weatherCache.getCurrentWeather()
    }

    override fun getForecasts(): Observable<List<WeatherRecordEntity>> {
        return weatherCache.getForecasts()
    }

    override fun saveWeather(currentWeatherRecord: WeatherRecordEntity, forecasts: List<WeatherRecordEntity>): Completable {
        return weatherCache.saveWeather(currentWeatherRecord, forecasts)
                .andThen(weatherCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearWeather(): Completable {
        return weatherCache.clearWeather()
    }
}