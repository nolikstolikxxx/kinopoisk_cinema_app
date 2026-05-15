package com.example.kinopoiskCinemaApp.presentation.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for home screen.
 *
 * Handles:
 * - Loading movie collections;
 * - Managing home screen state;
 * - Delivering movies data to UI layer.
 */
@HiltViewModel
class HomePageViewModel @Inject constructor(

    /**
     * UseCase for movie-related operations.
     */
    private val movieUseCase: MovieUseCase

) : ViewModel() {

    // ================================
    // Home State
    // ================================

    private val _state = MutableStateFlow(HomePageState())
    val state: StateFlow<HomePageState> = _state

    // ================================
    // Initialization
    // ================================

    init {

        getAllMovies()

    }

    // ================================
    // Movies
    // ================================

    /**
     * Loads all movie collections.
     *
     * Updates UI state:
     * - Loading;
     * - Success;
     * - Error.
     */
    private fun getAllMovies() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            try {
                val movies = movieUseCase.getAllFilms()
                _state.value = _state.value.copy(isLoading = false , movies = movies)

            } catch (e: HttpException) {
                _state.value = _state.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }
}