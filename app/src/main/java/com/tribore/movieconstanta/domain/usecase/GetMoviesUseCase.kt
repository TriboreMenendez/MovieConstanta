package com.tribore.movieconstanta.domain.usecase

import com.tribore.movieconstanta.domain.models.Movie
import com.tribore.movieconstanta.domain.repository.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> {
        return repository.getMovies()
    }

}