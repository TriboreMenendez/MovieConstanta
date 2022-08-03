package com.tribore.movieconstanta.di

import com.tribore.movieconstanta.presentation.HomeFragment
import com.tribore.movieconstanta.presentation.MainActivity
import dagger.Component

@App
@Component(modules = [DomainModule::class, DispatcherModule::class, ViewModelsModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)

}