package com.example.kinopoiskCinemaApp.presentation.gallery

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for movie gallery screen.
 *
 * Handles:
 * - Loading movie gallery images;
 * - Managing gallery screen state.
 */
class GalleryPageViewModel @Inject constructor(

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
    // Gallery State
    // ================================

    private val _state = MutableStateFlow(GalleryPageState())
    val state: StateFlow<GalleryPageState> = _state


    // ================================
    // Initialization
    // ================================

    init {
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        Log.d("id" , id.toString())
        if (id != null) {
            getGallery(id)
        }
    }

    // ================================
    // Gallery
    // ================================

    /**
     * Loads gallery images for selected movie.
     *
     * @param id Movie identifier.
     */
    fun getGallery(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val gallery = movieUseCase.getImages(id)

                _state.value = _state.value.copy(
                    isLoading = false ,
                    gallery = gallery
                )
            } catch (e: HttpException) {
                _state.value = _state.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }
}