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

class GalleryPageViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase ,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GalleryPageState())
    val state: StateFlow<GalleryPageState> = _state


    init {
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        Log.d("id" , id.toString())
        if (id != null) {
            getGallery(id)
        }

    }

    fun getGallery(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                var gallery = movieUseCase.getImages(id)

                _state.value = _state.value.copy(
                    isLoading = false ,
                    gallary = gallery
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