package com.example.kinopoiskCinemaApp.domain.model

data class Movie(
    val kinopoiskId: Int,
    val countries: List<Country>,
    val coverUrl: String,
    val description: String,
    val genres: List<Genre>,
    val logoUrl: String,
    val nameEn: String,
    val nameOriginal: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val ratingAgeLimits: String,
    val ratingKinopoisk: Double,
    val year: Int
)