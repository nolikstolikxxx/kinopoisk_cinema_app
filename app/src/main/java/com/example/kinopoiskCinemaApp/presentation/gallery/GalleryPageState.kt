package com.example.kinopoiskCinemaApp.presentation.gallery

import com.example.kinopoiskCinemaApp.domain.model.Images

data class GalleryPageState (
    var isLoading: Boolean = false ,
    var gallery: Images? = null ,
    var error: String = "" ,
    var id: Int? = 0
)