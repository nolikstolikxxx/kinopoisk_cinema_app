package com.example.kinopoiskCinemaApp.domain.model.enums

enum class MoviesCollectionType {
    TOP_250_MOVIES,
    VAMPIRE_THEME,
    COMICS_THEME;
}

fun getMoviesCollectionTypeFromString(type: String): MoviesCollectionType? {
    return when (type) {
        MoviesCollectionType.TOP_250_MOVIES.name -> MoviesCollectionType.TOP_250_MOVIES
        MoviesCollectionType.VAMPIRE_THEME.name -> MoviesCollectionType.VAMPIRE_THEME
        MoviesCollectionType.COMICS_THEME.name -> MoviesCollectionType.COMICS_THEME
        else -> null
    }
}

fun convertCollectionType(type: MoviesCollectionType) : String {
    return when (type) {
        MoviesCollectionType.TOP_250_MOVIES -> "Топ 250 Кинопоиска"
        MoviesCollectionType.COMICS_THEME -> "По комиксам"
        MoviesCollectionType.VAMPIRE_THEME -> "Про вампиров"
    }
}