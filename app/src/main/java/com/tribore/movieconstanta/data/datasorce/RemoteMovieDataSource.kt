package com.tribore.movieconstanta.data.datasorce

import com.tribore.movieconstanta.data.network.MovieApi
import com.tribore.movieconstanta.domain.models.Movie
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(private val movieApi: MovieApi) {

    suspend fun get(): List<Movie> {
        return movieApi.getMovies().items
    }

}