import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import ru.artyompugachev.data.WeatherDataRepository
import ru.artyompugachev.data.factory.WeatherDataFactory
import ru.artyompugachev.data.mapper.WeatherMapper
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.repository.WeatherCache
import ru.artyompugachev.data.repository.WeatherDataStore
import ru.artyompugachev.data.store.WeatherDataStoreFactory
import ru.artyompugachev.domain.model.Weather

class WeatherDataRepositoryTest {

    private val mapper = mock<WeatherMapper>()
    private val factory = mock<WeatherDataStoreFactory>()
    private val store = mock<WeatherDataStore>()
    private val cache = mock<WeatherCache>()
    private val repository = WeatherDataRepository(mapper, cache, factory)

    // todo refer to Five's book about what modules use

    @Before
    fun setUp() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreWeatherCached(Single.just(false))
        stubSaveWeather(Completable.complete())
    }


    @Test
    fun getProjectsCompletes() {
        stubGetWeather(Observable.just(WeatherDataFactory.makeWeatherEntity()))
        stubMapper(WeatherDataFactory.makeWeather(), any())

        val testObserver = repository.getWeather().test()
        testObserver.assertComplete()
    }


    @Test
    fun getProjectsReturnsData() {
        val weatherEntity = WeatherDataFactory.makeWeatherEntity()
        val weather = WeatherDataFactory.makeWeather()

        stubGetWeather(Observable.just(weatherEntity))
        stubMapper(weather, weatherEntity)

        val testObserver = repository.getWeather().test()
        testObserver.assertValue(weather)
    }


    private fun stubSaveWeather(completable: Completable) {
        whenever(store.saveWeather(any()))
                .thenReturn(completable)
    }


    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
                .thenReturn(store)
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }


    private fun stubGetWeather(observable: Observable<WeatherEntity>) {
        whenever(store.getWeather())
                .thenReturn(observable)
    }


    private fun stubIsCacheExpired(isExpired: Single<Boolean>) {
        whenever(cache.isExpired())
                .thenReturn(isExpired)
    }


    private fun stubAreWeatherCached(isWeatherCached: Single<Boolean>) {
        whenever(cache.isWeatherCached())
                .thenReturn(isWeatherCached)
    }


    private fun stubMapper(model: Weather, entity: WeatherEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }
}