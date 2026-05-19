package com.example.kinopoiskCinemaApp.domain.model

data class Staff(
    val age: Int ,
    val birthday: String ,
    val birthPlace: String ,
    val death: String ,
    val deathPlace: String ,
    val facts: List<String> ,
    val films: List<Film> ,
    val growth: String ,
    val hasAwards: Int ,
    val nameEn: String ,
    val nameRu: String ,
    val personId: Int ,
    val posterUrl: String ,
    val profession: String ,
    val sex: String ,
    val webUrl: String
)