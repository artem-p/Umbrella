package ru.artyompugachev.data.store

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class WeatherDataStoreFactoryTest {
    private val cacheStore = mock<WeatherCacheDataStore>()
    private val remoteStore = mock<WeatherRemoteDataStore>()
    private val factory = WeatherDataStoreFactory(cacheStore, remoteStore)

    // todo add weather data repository test

    @Test
    fun getDataStoreReturnsCacheStoreWhenCacheExists() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }


    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }


    @Test
    fun getDataStoreReturnsRemoteStoreWhenWeatherNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }


    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}