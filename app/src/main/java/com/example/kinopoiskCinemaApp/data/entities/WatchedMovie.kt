package com.example.kinopoiskCinemaApp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_movies")
data class WatchedMovie(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val posterUrl: String,
    val name: String,
    val rating: Double,
    val genre: String,
    val visitedAt: Long
)