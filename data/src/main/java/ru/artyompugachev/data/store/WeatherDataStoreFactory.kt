package ru.artyompugachev.data.store

import ru.artyompugachev.data.repository.WeatherDataStore
import javax.inject.Inject

open class WeatherDataStoreFactory @Inject constructor(
        private val weatherCacheDataStore: WeatherCacheDataStore,
        private val weatherRemoteDataStore: WeatherRemoteDataStore) {

    /**
     * Decide which data store we use
     * */
    open fun getDataStore(areWeatherCached: Boolean,
                          isCacheExpired: Boolean): WeatherDataStore {

        return if (areWeatherCached && !isCacheExpired) {
            weatherCacheDataStore
        } else {
            weatherRemoteDataStore
        }
    }


    /**
     * Sometimes we need just cache data store
     * */
    open fun getCacheDataStore(): WeatherDataStore {
        return weatherCacheDataStore
    }
}