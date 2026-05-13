package com.example.kinopoiskCinemaApp.presentation.staff_filmography

import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.ProfessionKey

data class StaffFilmographyState(
    var isLoading: Boolean = false ,
    var movies: Map<ProfessionKey , List<Movie>> = emptyMap() ,
    var staffName: String = "" ,
    var error: String = "" ,
    var professionKey: ProfessionKey = ProfessionKey.ACTOR
)