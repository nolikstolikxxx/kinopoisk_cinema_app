package com.example.kinopoiskCinemaApp.presentation.staff_detail

import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.Staff

data class StaffDetailState (
    var isLoading: Boolean = false ,
    var staff: Staff? = null ,
    var staffMovies: List<Movie> = emptyList() ,
    var error: String = "" ,
)