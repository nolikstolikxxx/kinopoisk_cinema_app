package com.example.kinopoiskCinemaApp.presentation.staff_detail_movie

import com.example.kinopoiskCinemaApp.domain.model.Movie

data class StaffDetailMovieState(
    var isLoading: Boolean = false ,
    var staffName: String = "" ,
    var staffMovies: List<Movie> = emptyList() ,
    var error: String? = ""
)