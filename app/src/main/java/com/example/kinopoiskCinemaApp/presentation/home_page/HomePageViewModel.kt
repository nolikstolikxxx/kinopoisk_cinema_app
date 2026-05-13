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

@HiltViewModel
class HomePageViewModel @Inject constructor (
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _state =  MutableStateFlow(HomePageState())
    val state: StateFlow<HomePageState> = _state


    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true)

            try {
                var movies = movieUseCase.getAllFilms()
                _state.value = _state.value.copy(isLoading = false, movies = movies)

            } catch (e: HttpException) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }
}