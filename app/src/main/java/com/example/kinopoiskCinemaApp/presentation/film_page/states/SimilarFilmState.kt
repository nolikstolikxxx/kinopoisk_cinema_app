package com.example.kinopoiskCinemaApp.presentation.film_page.states

import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies

data class SimilarFilmState (
    var isLoading: Boolean = false ,
    var movies: SimilarMovies? = null ,
    var error: String = "" ,
    var id: Int? = 0
)