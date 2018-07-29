package ru.artyompugachev.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import ru.artyompugachev.data.factory.WeatherDataFactory
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.repository.WeatherRemote

class WeatherRemoteDataStoreTest {
    private val remote = mock<WeatherRemote>()
    private val store = WeatherRemoteDataStore(remote)


    @Test
    fun getProjectsCompletes() {
        stubGetWeather(Observable.just(WeatherDataFactory.makeWeatherEntity()))

        val testObserver = store.getWeather().test()

        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val data = WeatherDataFactory.makeWeatherEntity()
        stubGetWeather(Observable.just(data))
        val testObserver = store.getWeather().test()

        testObserver.assertValue(data)
    }


    @Test
    fun getProjectsCallsRemote() {
        stubGetWeather(Observable.just(WeatherDataFactory.makeWeatherEntity()))

        store.getWeather()

        verify(remote).getWeather()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveWeather(WeatherDataFactory.makeWeatherEntity()).test()
    }


    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearWeather().test()
    }


    private fun stubGetWeather(observable: Observable<WeatherEntity>) {
        whenever(remote.getWeather())
                .thenReturn(observable)
    }
}