package com.tribore.movieconstanta.data.repository

import com.tribore.movieconstanta.data.datasorce.RemoteMovieDataSource
import com.tribore.movieconstanta.domain.models.Movie
import com.tribore.movieconstanta.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return withContext(dispatcher) {
            remoteDataSource.get()
        }
    }

}