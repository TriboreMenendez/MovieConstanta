package com.tribore.movieconstanta.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tribore.movieconstanta.presentation.MovieViewModel
import com.tribore.movieconstanta.presentation.ViewModelsFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @App
    @Binds
    fun bindViewModelFactory(vmProviderFactory: ViewModelsFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel

}