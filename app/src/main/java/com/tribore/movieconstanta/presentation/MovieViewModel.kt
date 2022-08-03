package com.tribore.movieconstanta.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribore.movieconstanta.domain.models.Movie
import com.tribore.movieconstanta.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovie: GetMoviesUseCase
): ViewModel() {

    private val _listMovies = MutableLiveData<List<Movie>>()
    val listMovies: LiveData<List<Movie>> = _listMovies

    init {
        loadData()
        Log.d("TAG", "Init vm")
    }

    private fun loadData() {
        viewModelScope.launch {
            _listMovies.value = getMovie.invoke()
        }
    }
}