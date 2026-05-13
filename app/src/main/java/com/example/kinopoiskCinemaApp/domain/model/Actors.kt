package com.example.kinopoiskCinemaApp.domain.model

data class Actors (
    val staffId: Int,
    val nameRu: String,
    val nameEn: String,
    val description: String,
    val posterUrl: String,
    val professionText: String,
    val professionKey: String
)