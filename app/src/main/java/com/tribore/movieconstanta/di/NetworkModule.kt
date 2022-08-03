package com.tribore.movieconstanta.di

import com.tribore.movieconstanta.data.network.MovieApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/"
    }

    @Provides
    fun provideRetrofitService(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}