package com.example.kinopoiskCinemaApp.data.network.dto

import com.example.kinopoiskCinemaApp.domain.model.Movie

data class CollectionMovieDto(
    val total: Int ,
    val totalPages: Int ,
    val items: List<Movie>
)
