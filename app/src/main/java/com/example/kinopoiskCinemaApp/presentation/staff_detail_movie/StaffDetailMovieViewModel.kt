package com.example.kinopoiskCinemaApp.presentation.staff_detail_movie

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
 * ViewModel responsible for staff movies screen.
 *
 * Handles:
 * - Loading movies related to selected staff member;
 * - Loading staff information;
 * - Managing UI state.
 */
@HiltViewModel
class StaffDetailMovieViewModel @Inject constructor(

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

    private val _state = MutableStateFlow(StaffDetailMovieState())
    val state: StateFlow<StaffDetailMovieState> = _state

    // ================================
    // Initialization
    // ================================

    init {
        val id: Int? = savedStateHandle.get<String>("staffId")?.toInt()

        if (id != null) {
            getStaffMovies(id)
        }
    }

    // ================================
    // Staff Movies
    // ================================

    /**
     * Loads movies related
     * to selected staff member.
     *
     * Also loads staff name.
     *
     * @param id Staff identifier.
     */
    private fun getStaffMovies(id: Int) {
        viewModelScope.launch {
            _state.value = StaffDetailMovieState(isLoading = true)

            try {
                val staff = staffUseCase.getStuffDetailsById(id)
                val staffMovies = mutableListOf<Movie>()

                /**
                 * Loads first 10 movies
                 * related to selected staff member.
                 */
                for (film in staff.films.take(10)) {
                    val mov = movieUseCase.getFilmById(film.filmId)
                    staffMovies.add(mov)
                }

                _state.value = StaffDetailMovieState(
                    staffName = staff.nameRu ,
                    staffMovies = staffMovies
                )

            } catch (e: HttpException) {
                _state.value = StaffDetailMovieState(error = e.message())
            }
        }

    }
}