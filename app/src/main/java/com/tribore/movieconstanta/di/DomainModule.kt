package com.tribore.movieconstanta.di

import com.tribore.movieconstanta.data.repository.MovieRepositoryImpl
import com.tribore.movieconstanta.domain.repository.MovieRepository
import com.tribore.movieconstanta.domain.usecase.GetMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DomainModule {

    companion object {
        @Provides
        fun provideGetMovieUseCase(repository: MovieRepository) = GetMoviesUseCase(repository)
    }

    @Binds
    @App
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}