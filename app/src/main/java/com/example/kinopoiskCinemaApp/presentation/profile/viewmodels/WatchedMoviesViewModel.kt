package com.example.kinopoiskCinemaApp.presentation.profile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao
import com.example.kinopoiskCinemaApp.presentation.profile.states.WatchedMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for managing the full
 * watched movies history screen.
 *
 * Handles:
 * - Loading all watched movies;
 * - Updating UI state;
 * - Clearing watched history;
 * - Error handling.
 */
@HiltViewModel
class WatchedMoviesViewModel @Inject constructor(
    private val watchedMovieDao: WatchedMovieDao
) : ViewModel() {

    // ================================
    // State
    // ================================

    /**
     * Internal mutable state for watched movies.
     */
    private val _watchedMoviesState = MutableStateFlow(WatchedMoviesState())

    /**
     * Public immutable state exposed to the UI layer.
     */
    val watchedMoviesState: StateFlow<WatchedMoviesState> = _watchedMoviesState

    // ================================
    // Initialization
    // ================================

    init {
        getWatchedMovies()
    }

    // ================================
    // Load Watched Movies
    // ================================

    /**
     * Loads all watched movies from the local database.
     *
     * Updates loading state before and after request execution.
     * Handles possible request exceptions.
     */
    fun getWatchedMovies() {
        viewModelScope.launch {
            _watchedMoviesState.value = _watchedMoviesState.value.copy(isLoading = true)

            try {

                val movies = watchedMovieDao.getAllVisitedMovies()

                _watchedMoviesState.value = _watchedMoviesState.value.copy(
                    isLoading = false ,
                    movies = movies ,
                )

            } catch (e: HttpException) {
                _watchedMoviesState.value = _watchedMoviesState.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

    // ================================
    // Clear History
    // ================================

    /**
     * Clears all watched movies history
     * from the local database.
     */
    fun clear() {
        viewModelScope.launch {
            watchedMovieDao.clear()
            _watchedMoviesState.value = _watchedMoviesState.value.copy(
                isLoading = false ,
                movies = emptyList() ,
            )
        }
    }
}