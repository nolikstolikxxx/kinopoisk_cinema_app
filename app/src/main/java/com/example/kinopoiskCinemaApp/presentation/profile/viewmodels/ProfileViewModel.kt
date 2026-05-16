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
 * ViewModel responsible for managing the Profile screen state.
 *
 * Handles:
 * - Loading watched movies history;
 * - Updating UI state;
 * - Clearing movies history;
 * - Error handling during database operations.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(

    /**
     * DAO for accessing watched movies history.
     */
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
     * Public immutable state exposed to the UI.
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
     * Loads recently watched movies from the local database.
     *
     * Updates loading state before and after data request.
     * Handles possible HTTP exceptions.
     */
    fun getWatchedMovies() {
        viewModelScope.launch {
            _watchedMoviesState.value = _watchedMoviesState.value.copy(isLoading = true)

            try {
                val movies = watchedMovieDao.getRecentVisitedMovies()

                _watchedMoviesState.value = _watchedMoviesState.value.copy(
                    isLoading = false ,
                    movies = movies ,
                )

            } catch (e: Exception) {
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
     * Clears watched movies history from the database
     * and resets UI state.
     */
    fun clearHistory() {
        viewModelScope.launch {
            watchedMovieDao.clear()
            _watchedMoviesState.value = WatchedMoviesState(isLoading = false , movies = emptyList())
        }
    }
}