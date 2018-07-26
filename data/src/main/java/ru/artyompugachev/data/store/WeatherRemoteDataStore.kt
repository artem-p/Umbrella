package ru.artyompugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.repository.WeatherDataStore
import ru.artyompugachev.data.repository.WeatherRemote
import javax.inject.Inject

open class WeatherRemoteDataStore @Inject constructor(private val weatherRemote: WeatherRemote):
        WeatherDataStore {
    override fun getCurrentWeather(): Observable<WeatherEntity> {
        return weatherRemote.getCurrentWeather()
    }

    override fun getForecasts(): Observable<List<WeatherEntity>> {
        return weatherRemote.getForecasts()
    }

    override fun saveWeather(currentWeather: WeatherEntity, forecasts: List<WeatherEntity>): Completable {
        throw UnsupportedOperationException("Saving weather isn't supported in the remote data store...")
    }

    override fun clearWeather(): Completable {
        throw UnsupportedOperationException("Clearing weather isn't supported in the remote data store...")
    }
}