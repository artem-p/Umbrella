package ru.artyompugachev.domain.repository

import io.reactivex.Observable
import ru.artyompugachev.domain.model.Place

interface PlaceRepository {

    fun getPlace(): Observable<Place>
}