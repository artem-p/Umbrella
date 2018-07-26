package ru.artyompugachev.data

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import ru.artyompugachev.data.mapper.WeatherRecordMapper
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.store.WeatherDataStoreFactory
import ru.artyompugachev.domain.model.WeatherRecord
import ru.artyompugachev.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherDataRepository @Inject constructor(
        private val mapper: WeatherRecordMapper,
        private val cache: WeatherCache,
        private val factory: WeatherDataStoreFactory
): WeatherRepository {

    override fun getCurrentWeather(): Observable<WeatherRecord> {

        return Observable.zip(cache.isWeatherCached().toObservable(),
                cache.isWeatherCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second).getCurrentWeather()
                }

                .flatMap { weather ->
                    factory.getCacheDataStore()
                            .saveWeather(weather)
                            .andThen(Observable.just(weather))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun getForecasts(): Observable<List<WeatherRecord>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}