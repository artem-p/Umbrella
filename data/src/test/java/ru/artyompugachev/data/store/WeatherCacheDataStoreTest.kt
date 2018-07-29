package ru.artyompugachev.data.store

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
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


    private fun stubWeatherCacheGetWeather(observable: Observable<WeatherEntity>) {
        whenever(cache.getWeather())
                .thenReturn(observable)
    }
}