package com.example.kinopoiskCinemaApp.presentation.film_page.states

import com.example.kinopoiskCinemaApp.domain.model.Images

data class GalleryState (
    var isLoading: Boolean = false ,
    var gallary: Images? = null ,
    var error: String = "" ,
    var id: Int? = 0
)