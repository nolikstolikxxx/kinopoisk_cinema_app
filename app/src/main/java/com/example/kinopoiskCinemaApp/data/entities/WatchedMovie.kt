package com.example.kinopoiskCinemaApp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity representing watched movie history item.
 *
 * Stored in:
 * - watched_movies table.
 */
@Entity(tableName = "watched_movies")
data class WatchedMovie(

    /**
     * Unique movie identifier.
     */
    @PrimaryKey(autoGenerate = false)
    val movieId: Int ,

    /**
     * URL of movie poster image.
     */
    val posterUrl: String ,

    /**
     * Movie title.
     */
    val name: String ,

    /**
     * Movie rating value.
     */
    val rating: Double ,

    /**
     * Main movie genre.
     */
    val genre: String ,

    /**
     * Timestamp when movie was visited.
     */
    val visitedAt: Long
)