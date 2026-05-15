package com.example.kinopoiskCinemaApp.presentation.home_page

import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType

data class HomePageState(
    var isLoading: Boolean = false ,
    var movies: Map<MoviesCollectionType , List<Movie>> = emptyMap() ,
    var error: String = "" ,
)
