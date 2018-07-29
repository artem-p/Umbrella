package ru.artyompugachev.data.store

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.artyompugachev.data.factory.WeatherDataFactory
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.repository.WeatherCache

@RunWith(JUnit4::class)
class WeatherCacheDataStoreTest {

    private val cache = mock<WeatherCache>()
    private val store = WeatherCacheDataStore(cache)


    @Test
    fun getProjectsCompletes() {
        stubWeatherCacheGetWeather(Observable.just(WeatherDataFactory.makeWeatherEntity()))
        val testObserver = store.getWeather().test()

        testObserver.assertComplete()
    }


    @Test
    fun getWeatherReturnsData() {
        val data = WeatherDataFactory.makeWeatherEntity()
        stubWeatherCacheGetWeather(Observable.just(data))

        val testObserver = store.getWeather().test()
        testObserver.assertValue(data)
    }


    @Test
    fun getWeatherCallsCacheSource() {
        stubWeatherCacheGetWeather(Observable.just(WeatherDataFactory.makeWeatherEntity()))
        store.getWeather()

        verify(cache).getWeather()
    }


    @Test
    fun saveWeatherCompletes() {
        stubSaveWeather(Completable.complete())
        stubWeatherCacheSetLastCacheTime(Completable.complete())

        val testObserver = store.saveWeather(WeatherDataFactory.makeWeatherEntity()).test()

        testObserver.assertComplete()
    }

    @Test
    fun saveWeatherCallsCache() {
        stubSaveWeather(Completable.complete())
        stubWeatherCacheSetLastCacheTime(Completable.complete())

        store.saveWeather(WeatherDataFactory.makeWeatherEntity()).test()

        verify(cache).saveWeather(any())
    }

    @Test
    fun clearProjectsCompletes() {
        stubClearWeather(Completable.complete())

        val testObserver = store.clearWeather().test()
        testObserver.assertComplete()
    }


    @Test
    fun clearProjectsCallsCache() {
        stubClearWeather(Completable.complete())

        store.clearWeather()
        verify(cache).clearWeather()
    }


    private fun stubWeatherCacheGetWeather(observable: Observable<WeatherEntity>) {
        whenever(cache.getWeather())
                .thenReturn(observable)
    }

    private fun stubSaveWeather(completable: Completable) {
        whenever(cache.saveWeather(any()))
                .thenReturn(completable)
    }


    private fun stubWeatherCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }

    private fun stubClearWeather(completable: Completable) {
        whenever(cache.clearWeather())
                .thenReturn(completable)
    }
}