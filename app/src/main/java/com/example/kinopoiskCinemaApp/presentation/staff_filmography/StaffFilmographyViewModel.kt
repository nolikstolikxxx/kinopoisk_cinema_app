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

/**
 * ViewModel responsible for staff filmography screen.
 *
 * Handles:
 * - Loading staff filmography;
 * - Grouping movies by profession;
 * - Managing selected profession type;
 * - Managing UI state.
 */
@HiltViewModel
class StaffFilmographyViewModel @Inject constructor(

    /**
     * UseCase for movie-related operations.
     */
    private val movieUseCase: MovieUseCase ,

    /**
     * UseCase for staff-related operations.
     */
    private val staffUseCase: StaffUseCase ,

    /**
     * Saved state containing navigation arguments.
     */
    savedStateHandle: SavedStateHandle ,

    ) : ViewModel() {

    // ================================
    // Screen State
    // ================================

    private val _state = MutableStateFlow(StaffFilmographyState())
    val state: StateFlow<StaffFilmographyState> = _state

    // ================================
    // Initialization
    // ================================

    init {
        val id: Int? = savedStateHandle.get<String>("staffId")?.toInt()

        if (id != null) {
            getStaffFilmographyById(id)
        }
    }

    // ================================
    // Profession Type
    // ================================

    /**
     * Changes currently selected
     * profession category.
     *
     * @param professionKey Selected profession type.
     */
    fun changeFilmographyType(professionKey: ProfessionKey) {
        _state.value = _state.value.copy(professionKey = professionKey)
    }

    // ================================
    // Filmography
    // ================================

    /**
     * Loads full filmography
     * for selected staff member.
     *
     * Movies are grouped by profession.
     *
     * @param id Staff identifier.
     */
    private fun getStaffFilmographyById(id: Int) {
        viewModelScope.launch {
            _state.value = StaffFilmographyState(isLoading = true)

            /**
             * Default selected profession.
             */
            var initialKey: ProfessionKey = ProfessionKey.ACTOR

            try {
                val staff = staffUseCase.getStuffDetailsById(id)
                val staffFilms = staff.films

                /**
                 * Map containing movies
                 * grouped by profession type.
                 */
                val moviesMap:
                        MutableMap<ProfessionKey , MutableList<Movie>> =
                    mutableMapOf()

                staffFilms.forEachIndexed { _ , film ->
                    val movie = movieUseCase.getFilmById(film.filmId)

                    val professionKey = getProfessionKeyFromString(film.professionKey)
                    val moviesForKey = moviesMap.getOrPut(professionKey) { mutableListOf() }

                    moviesForKey.add(movie)
                    initialKey = professionKey
                }


                _state.value = StaffFilmographyState(
                    movies = moviesMap ,
                    staffName = staff.nameRu ,
                    professionKey = initialKey
                )
            } catch (_: Exception) {
                _state.value = StaffFilmographyState(error = "Unexpected error occurred")
            }
        }
    }
}