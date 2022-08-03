package com.tribore.movieconstanta.domain.models

data class Movie(
    val actors: List<Actor>,
    val directorName: String,
    val releaseYear: Int,
    val title: String
)