package com.tribore.movieconstanta.presentation


sealed class ResultState<T> {
    class Load<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val message: String) : ResultState<T>()
}
