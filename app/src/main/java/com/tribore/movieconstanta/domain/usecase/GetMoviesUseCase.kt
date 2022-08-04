package com.tribore.movieconstanta.domain.usecase

import com.tribore.movieconstanta.domain.models.Actor
import com.tribore.movieconstanta.domain.models.Movie
import com.tribore.movieconstanta.domain.repository.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> {

        val resultList = repository.getMovies()
            .sortedBy {
                it.releaseYear
            }.map {
                Movie(
                    actors = it.actors.toSet().toList(),
                    directorName = refactorDirectorName(it.directorName),
                    releaseYear = it.releaseYear,
                    title = it.title
                )
            }

        return resultList
    }

    private fun refactorDirectorName(name: String): String {
        val divider = " "
        val indexFirstName = 0
        val indexMiddleName = 1
        val listWord = name.split(divider)

        return "${listWord.last()} ${listWord[indexFirstName].first()}.${listWord[indexMiddleName].first()}"
    }

}