package ru.artyompugachev.domain.usecases.browse

import io.reactivex.Observable
import ru.artyompugachev.domain.executor.PostExecutionThread
import ru.artyompugachev.domain.model.Weather
import ru.artyompugachev.domain.repository.WeatherRepository
import ru.artyompugachev.domain.usecases.ObservableUseCase
import javax.inject.Inject


class GetForecasts @Inject constructor(
        private val weatherRepository: WeatherRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Weather>, Nothing>(postExecutionThread) {


    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Weather>> {
        return weatherRepository.getForecasts()
    }
}