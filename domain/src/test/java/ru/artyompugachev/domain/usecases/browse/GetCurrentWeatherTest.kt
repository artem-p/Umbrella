package ru.artyompugachev.domain.usecases.browse

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.artyompugachev.domain.data.WeatherDataFactory
import ru.artyompugachev.domain.executor.PostExecutionThread
import ru.artyompugachev.domain.model.Weather
import ru.artyompugachev.domain.repository.WeatherRepository

class GetCurrentWeatherTest {
    private lateinit var getCurrentWeather: GetCurrentWeather

    @Mock lateinit var weatherRepository: WeatherRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getCurrentWeather = GetCurrentWeather(weatherRepository, postExecutionThread)
    }


    @Test
    fun getCurrentWeatherCompletes() {
        stubGetCurrentWeather(Observable.just(WeatherDataFactory.makeWeather()))
        val testObserver = getCurrentWeather.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }


    @Test
    fun getCurrentWeatherReturnsData() {
        val weather = WeatherDataFactory.makeWeather()
        stubGetCurrentWeather(Observable.just(weather))
        val testObserver = getCurrentWeather.buildUseCaseObservable().test()
        testObserver.assertValue(weather)
    }


    fun stubGetCurrentWeather(stubWeather: Observable<Weather>) {
        whenever(weatherRepository.getCurrentWeather())
                .thenReturn(stubWeather)
    }
}