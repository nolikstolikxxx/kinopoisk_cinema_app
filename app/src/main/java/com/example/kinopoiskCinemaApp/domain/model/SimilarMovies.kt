package com.example.kinopoiskCinemaApp.domain.model

data class SimilarMovies(
    val total: Int ,
    val items: List<SimilarMovie> ,
)

data class SimilarMovie(
    val filmId: Int ,
    val nameRu: String ,
    val nameEn: String ,
    val nameOriginal: String ,
    val posterUrl: String ,
    val posterUrlPreview: String ,
    val relationType: String
)