package ru.artyompugachev.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity
import ru.artyompugachev.data.repository.WeatherDataStore
import ru.artyompugachev.data.repository.WeatherRemote
import javax.inject.Inject

open class WeatherRemoteDataStore @Inject constructor(private val weatherRemote: WeatherRemote):
        WeatherDataStore {

    override fun getWeather(): Observable<WeatherEntity> {
        return weatherRemote.getWeather()
    }


    override fun saveWeather(weather: WeatherEntity): Completable {
        throw UnsupportedOperationException("Saving weather isn't supported in the remote data store...")
    }


    override fun clearWeather(): Completable {
        throw UnsupportedOperationException("Clearing weather isn't supported in the remote data store...")
    }
}