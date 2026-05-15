package com.example.kinopoiskCinemaApp.presentation.staff_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.example.kinopoiskCinemaApp.domain.usecase.StaffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for staff details screen.
 *
 * Handles:
 * - Loading staff information;
 * - Loading movies related to selected staff member;
 * - Managing UI state.
 */
@HiltViewModel
class StaffDetailViewModel @Inject constructor(

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

    private val _state = MutableStateFlow(StaffDetailState())
    val state: StateFlow<StaffDetailState> = _state

    // ================================
    // Initialization
    // ================================

    init {
        val id: Int? = savedStateHandle.get<String>("staffId")?.toInt()

        if (id != null) {
            getStaffDetails(id)
        }
    }

    // ================================
    // Staff Details
    // ================================

    /**
     * Loads detailed information
     * about selected staff member.
     *
     * Also loads a preview list
     * of staff-related movies.
     *
     * @param id Staff identifier.
     */
    private fun getStaffDetails(id: Int) {
        viewModelScope.launch {

            _state.value = StaffDetailState(isLoading = true)

            try {
                val staff = staffUseCase.getStuffDetailsById(id)
                val staffMovies = mutableListOf<Movie>()

                /**
                 * Loads first 7 movies
                 * related to selected staff member.
                 */
                for (film in staff.films.take(7)) {
                    val mov = movieUseCase.getFilmById(film.filmId)
                    staffMovies.add(mov)
                }

                _state.value = StaffDetailState(
                    staff = staff ,
                    staffMovies = staffMovies ,
                )

            } catch (e: HttpException) {
                _state.value = StaffDetailState(error = e.message())
            }
        }
    }
}