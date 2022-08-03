package com.tribore.movieconstanta

import android.app.Application
import com.tribore.movieconstanta.di.AppComponent
import com.tribore.movieconstanta.di.DaggerAppComponent

class MovieApp: Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}