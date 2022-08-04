package com.tribore.movieconstanta.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribore.movieconstanta.domain.models.Movie
import com.tribore.movieconstanta.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovie: GetMoviesUseCase
) : ViewModel() {

    private val _responseStatus = MutableLiveData<ResultState<List<Movie>>>()
    val responseStatus: LiveData<ResultState<List<Movie>>> = _responseStatus

    init {
        loadData()
    }

    fun loadData() {
        _responseStatus.value = ResultState.Load()

        viewModelScope.launch {
            try {
                val resultListMovies = getMovie()
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

                _responseStatus.value = ResultState.Success(data = resultListMovies)
            } catch (e: Exception) {
                _responseStatus.value = ResultState.Error(message = e.message ?: "Unknown error")
            }
        }
    }

    private fun refactorDirectorName(name: String): String {
        val divider = " "
        val indexFirstName = 0
        val indexMiddleName = 1
        val listWord = name.split(divider)

        return if (listWord.size == 1) {
            listWord.first()
        } else "${listWord.last()} ${listWord[indexFirstName].first()}.${listWord[indexMiddleName].first()}"
    }
}