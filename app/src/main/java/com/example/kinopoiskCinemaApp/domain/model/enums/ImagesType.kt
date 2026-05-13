package com.example.kinopoiskCinemaApp.domain.model.enums

enum class ImagesType {
    STILL,
    SHOOTING,
    POSTER,
    FAN_ART,
    PROMO,
    CONCEPT,
    WALLPAPER,
    COVER,
    SCREENSHOT
}

fun getImagesTypeFromString(type: String): ImagesType? {
    return when (type) {
        ImagesType.STILL.name -> ImagesType.STILL
        ImagesType.SHOOTING.name -> ImagesType.SHOOTING
        ImagesType.POSTER.name -> ImagesType.POSTER
        ImagesType.FAN_ART.name -> ImagesType.FAN_ART
        ImagesType.PROMO.name -> ImagesType.PROMO
        ImagesType.CONCEPT.name -> ImagesType.CONCEPT
        ImagesType.WALLPAPER.name -> ImagesType.WALLPAPER
        ImagesType.COVER.name -> ImagesType.COVER
        ImagesType.SCREENSHOT.name -> ImagesType.SCREENSHOT
        else -> null
    }
}

fun convertImagesType(type: ImagesType) : String {
    return when (type) {
        ImagesType.STILL -> "Стандартные"
        ImagesType.SHOOTING -> "Киносьемка"
        ImagesType.POSTER -> "Постер"
        ImagesType.FAN_ART -> "Фан арт"
        ImagesType.PROMO -> "Промо"
        ImagesType.CONCEPT -> "Концепция"
        ImagesType.WALLPAPER -> "Обои"
        ImagesType.COVER -> "Обложка"
        ImagesType.SCREENSHOT -> "Скриншот"
    }
}