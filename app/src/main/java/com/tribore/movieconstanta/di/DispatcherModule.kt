package com.tribore.movieconstanta.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatcherModule {

    @App
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}