package com.tribore.movieconstanta.data.network

import com.tribore.movieconstanta.domain.models.ListMovies
import retrofit2.http.GET

interface MovieApi {

    @GET("/constanta-android-dev/intership-wellcome-task/main/films.json")
    suspend fun getMovies(): ListMovies

}