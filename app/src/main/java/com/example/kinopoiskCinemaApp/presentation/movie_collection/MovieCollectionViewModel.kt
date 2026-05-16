package com.example.kinopoiskCinemaApp.presentation.movie_collection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.model.enums.getMoviesCollectionTypeFromString
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for movie collection screen.
 *
 * Handles:
 * - Loading movies by collection type;
 * - Managing collection screen state;
 * - Receiving navigation arguments.
 */
@HiltViewModel
class MovieCollectionViewModel @Inject constructor(

    /**
     * UseCase for movie-related operations.
     */
    private val movieUseCase: MovieUseCase ,

    /**
     * Saved state containing navigation arguments.
     */
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    // ================================
    // Collection State
    // ================================

    private val _state = MutableStateFlow(MovieCollectionState())
    val state: StateFlow<MovieCollectionState> = _state

    // ================================
    // Initialization
    // ================================

    init {
        val typeString = savedStateHandle.get<String>("type")

        val collectionType = typeString?.let { tp ->
            getMoviesCollectionTypeFromString(tp)
        }

        if (collectionType != null) {
            getMoviesByCollection(collectionType)
        }
    }

    // ================================
    // Movies Collection
    // ================================

    /**
     * Loads movies for selected collection type.
     *
     * Updates UI state:
     * - Loading;
     * - Success;
     * - Error.
     *
     * @param moviesCollectionType Collection type.
     */
    fun getMoviesByCollection(
        moviesCollectionType: MoviesCollectionType
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val movies = movieUseCase.getFilmsByCollectionType(moviesCollectionType)

                _state.value = _state.value.copy(
                    isLoading = false ,
                    movies = movies ,
                    collectionType = moviesCollectionType
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }
}