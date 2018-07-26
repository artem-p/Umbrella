package ru.artyompugachev.data.repository

import io.reactivex.Observable
import ru.artyompugachev.data.model.WeatherEntity
import ru.artyompugachev.data.model.WeatherRecordEntity


interface WeatherRemote {

    fun getWeather(): Observable<WeatherEntity>
}