package com.example.kinopoiskCinemaApp.presentation.profile.states

import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie

/**
 * UI state for watched movies screens.
 *
 * Contains:
 * - Loading state;
 * - List of watched movies;
 * - Error message if request fails.
 */
data class WatchedMoviesState(

    /**
     * Indicates whether data is currently loading.
     */
    var isLoading: Boolean = false ,

    /**
     * List of watched movies displayed on the screen.
     */
    var movies: List<WatchedMovie> = emptyList() ,

    /**
     * Error message shown when loading fails.
     */
    var error: String = "" ,
)