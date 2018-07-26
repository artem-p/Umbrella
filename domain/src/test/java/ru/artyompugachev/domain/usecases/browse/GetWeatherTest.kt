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

class GetWeatherTest {
    private lateinit var getWeather: GetWeather

    @Mock lateinit var weatherRepository: WeatherRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getWeather = GetWeather(weatherRepository, postExecutionThread)
    }


    @Test
    fun getWeatherCompletes() {
        stubGetWeather(Observable.just(WeatherDataFactory.makeWeather()))
        val testObserver = getWeather.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }


    @Test
    fun getWeatherReturnsData() {
        val weather = WeatherDataFactory.makeWeather()
        stubGetWeather(Observable.just(weather))
        val testObserver = getWeather.buildUseCaseObservable().test()
        testObserver.assertValue(weather)
    }


    private fun stubGetWeather(stubWeather: Observable<Weather>) {
        whenever(weatherRepository.getWeather())
                .thenReturn(stubWeather)
    }
}