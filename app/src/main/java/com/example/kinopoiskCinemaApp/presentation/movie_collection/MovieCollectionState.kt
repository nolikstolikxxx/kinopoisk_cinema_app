package com.example.kinopoiskCinemaApp.presentation.movie_collection

import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType

data class MovieCollectionState (
    var isLoading: Boolean = false ,
    var movies: List<Movie> = emptyList() ,
    var error: String = "" ,
    var collectionType: MoviesCollectionType? = null
)