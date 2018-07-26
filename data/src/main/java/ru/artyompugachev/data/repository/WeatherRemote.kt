package ru.artyompugachev.data.repository

import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherRecordEntity


interface WeatherRemote {

    fun getCurrentWeather(): Observable<WeatherRecordEntity>

    fun getForecasts(): Observable<List<WeatherRecordEntity>>
}