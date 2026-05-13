package com.example.kinopoiskCinemaApp.presentation.staff_filmography

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.ProfessionKey
import com.example.kinopoiskCinemaApp.domain.model.enums.getProfessionKeyFromString
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.example.kinopoiskCinemaApp.domain.usecase.StaffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaffFilmographyViewModel@Inject constructor(
    private val movieUseCase: MovieUseCase ,
    private val staffUseCase: StaffUseCase ,
    savedStateHandle: SavedStateHandle ,
): ViewModel() {

    private val _state = MutableStateFlow(StaffFilmographyState())
    val state: StateFlow<StaffFilmographyState> = _state

    init {
        val id: Int? = savedStateHandle.get<String>("staffId")?.toInt()

        if (id != null) {
            getStaffFilmographyById(id)
        }
    }

    fun changeFilmographyType(professionKey: ProfessionKey) {
        _state.value = _state.value.copy(professionKey = professionKey)
    }

    private fun getStaffFilmographyById(id: Int) {
        viewModelScope.launch {
            _state.value = StaffFilmographyState(isLoading = true)
            var initialKey: ProfessionKey = ProfessionKey.ACTOR

            try {
                val staff = staffUseCase.getStuffDetailsById(id)
                val staffFilms = staff.films

                val moviesMap: MutableMap<ProfessionKey, MutableList<Movie>> = mutableMapOf()

                staffFilms.forEachIndexed { _, film ->
                    val movie = movieUseCase.getFilmById(film.filmId)

                    val professionKey = getProfessionKeyFromString(film.professionKey)
                    val moviesForKey = moviesMap.getOrPut(professionKey) { mutableListOf() }

                    moviesForKey.add(movie)
                    initialKey = professionKey
                }


                _state.value = StaffFilmographyState(
                    movies = moviesMap,
                    staffName = staff.nameRu,
                    professionKey = initialKey
                )
            } catch (e: Exception) {
                _state.value = StaffFilmographyState(error = "Unexpected error occured")
            }
        }
    }
}