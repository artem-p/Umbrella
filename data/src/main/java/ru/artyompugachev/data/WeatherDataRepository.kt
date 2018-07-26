package ru.artyompugachev.data

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import ru.artyompugachev.data.mapper.WeatherMapper
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.store.WeatherDataStoreFactory
import ru.artyompugachev.domain.model.Weather
import ru.artyompugachev.domain.model.WeatherRecord
import ru.artyompugachev.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherDataRepository @Inject constructor(
        private val mapper: WeatherMapper,
        private val cache: WeatherCache,
        private val factory: WeatherDataStoreFactory)
    : WeatherRepository {

    override fun getWeather(): Observable<Weather> {

        return Observable.zip(cache.isWeatherCached().toObservable(),
                cache.isExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getWeather()
                }

                .flatMap { weather ->
                    factory.getCacheDataStore()
                            .saveWeather(weather)
                            .andThen(Observable.just(weather))
                }
                .map { mapper.mapFromEntity(it) }
    }
}