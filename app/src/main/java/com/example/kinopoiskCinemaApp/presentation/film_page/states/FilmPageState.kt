package com.example.kinopoiskCinemaApp.presentation.film_page.states

import com.example.kinopoiskCinemaApp.domain.model.DetailMovie

data class FilmPageState (
    var isLoading: Boolean = false ,
    var movie: DetailMovie? = null ,
    var error: String = "" ,
    var filmId: Int? = 0 ,
)