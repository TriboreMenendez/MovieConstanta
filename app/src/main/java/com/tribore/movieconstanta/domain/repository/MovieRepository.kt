package com.tribore.movieconstanta.domain.repository

import com.tribore.movieconstanta.domain.models.Movie

interface MovieRepository {

    suspend fun getMovies(): List<Movie>

}