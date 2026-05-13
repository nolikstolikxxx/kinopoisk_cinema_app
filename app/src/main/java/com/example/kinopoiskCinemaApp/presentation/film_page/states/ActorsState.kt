package com.example.kinopoiskCinemaApp.presentation.film_page.states

import com.example.kinopoiskCinemaApp.domain.model.Actors

data class ActorsState(
    var isLoading: Boolean = false ,
    var actor: List<Actors> = emptyList() ,
    var error: String = "" ,
    var id: Int? = 0
)