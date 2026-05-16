package com.example.kinopoiskCinemaApp.presentation.film_page

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.example.kinopoiskCinemaApp.presentation.film_page.states.ActorsState
import com.example.kinopoiskCinemaApp.presentation.film_page.states.FilmPageState
import com.example.kinopoiskCinemaApp.presentation.film_page.states.GalleryState
import com.example.kinopoiskCinemaApp.presentation.film_page.states.SimilarFilmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

/**
 * ViewModel responsible for movie details screen.
 *
 * Handles:
 * - Loading detailed movie information;
 * - Loading actors;
 * - Loading gallery images;
 * - Loading similar movies;
 * - Managing watched movies history.
 */
@HiltViewModel
class FilmPageViewModel @Inject constructor(

    /**
     * UseCase for movie-related operations.
     */
    private val movieUseCase: MovieUseCase ,

    /**
     * DAO for watched movies history.
     */
    private val watchedMovieDao: WatchedMovieDao ,

    /**
     * Saved state containing navigation arguments.
     */
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    // ================================
    // Movie State
    // ================================

    private val _stateMovie = MutableStateFlow(FilmPageState())
    val stateMovie: StateFlow<FilmPageState> = _stateMovie

    // ================================
    // Actors State
    // ================================

    private val _stateActors = MutableStateFlow(ActorsState())
    val stateActors: StateFlow<ActorsState> = _stateActors

    // ================================
    // Gallery State
    // ================================

    private val _stateGallery = MutableStateFlow(GalleryState())
    val stateGallery: StateFlow<GalleryState> = _stateGallery

    // ================================
    // Similar Movies State
    // ================================

    private val _stateSimilarFilm = MutableStateFlow(SimilarFilmState())
    val stateSimilarFilm: StateFlow<SimilarFilmState> = _stateSimilarFilm

    // ================================
    // Watched State
    // ================================

    private val _isWatched = MutableStateFlow(false)
    val isWatched: StateFlow<Boolean> = _isWatched

    // ================================
    // Initialization
    // ================================

    init {
        val id: Int? = savedStateHandle.get<String>("id")?.toInt()
        Log.d("id" , id.toString())

        if (id != null) {
            getMovieById(id)
            getActors(id)
            getGallery(id)
            getSimilarMovies(id)
        }
    }

    // ================================
    // Movie Details
    // ================================

    /**
     * Loads detailed movie information.
     *
     * Also checks whether movie exists
     * in watched history.
     *
     * @param id Movie identifier.
     */
    fun getMovieById(id: Int) {

        viewModelScope.launch {
            _stateMovie.value = _stateMovie.value.copy(isLoading = true)

            try {
                val movie = movieUseCase.getDetailFilm(id)

                _isWatched.value = watchedMovieDao.getMovieById(id) != null
                _stateMovie.value = _stateMovie.value.copy(
                    isLoading = false ,
                    movie = movie ,
                )
            } catch (e: Exception) {
                _stateMovie.value = _stateMovie.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

    // ================================
    // Actors
    // ================================

    /**
     * Loads actors list for selected movie.
     *
     * @param id Movie identifier.
     */
    fun getActors(id: Int) {
        viewModelScope.launch {
            _stateActors.value = _stateActors.value.copy(isLoading = true)

            try {
                val actors = movieUseCase.getActors(id)

                _stateActors.value = _stateActors.value.copy(
                    isLoading = false ,
                    actor = actors
                )
            } catch (e: Exception) {
                _stateActors.value = _stateActors.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
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
            _stateGallery.value = _stateGallery.value.copy(isLoading = true)

            try {
                val gallery = movieUseCase.getImages(id)

                _stateGallery.value = _stateGallery.value.copy(
                    isLoading = false ,
                    gallary = gallery
                )
            } catch (e: Exception) {
                _stateGallery.value = _stateGallery.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

    // ================================
    // Similar Movies
    // ================================

    /**
     * Loads movies similar to selected movie.
     *
     * @param id Movie identifier.
     */
    fun getSimilarMovies(id: Int) {
        viewModelScope.launch {
            _stateSimilarFilm.value = _stateSimilarFilm.value.copy(isLoading = true)

            try {
                val movie = movieUseCase.getSimilarMovies(id)

                _stateSimilarFilm.value = _stateSimilarFilm.value.copy(
                    isLoading = false ,
                    movies = movie
                )
            } catch (e: Exception) {
                _stateSimilarFilm.value = _stateSimilarFilm.value.copy(
                    isLoading = false ,
                    error = e.localizedMessage ?: "An unexpected error occurred"
                )
            }
        }
    }

    // ================================
    // Watched Movies
    // ================================

    /**
     * Adds movie to watched history.
     *
     * @param movie Detailed movie object.
     */
    fun insertMovieToWatched(movie: DetailMovie) {
        viewModelScope.launch {
            val watchedMovie = WatchedMovie(
                movieId = movie.kinopoiskId ,
                posterUrl = movie.posterUrl ,
                name = movie.nameEn ,
                rating = movie.ratingKinopoisk ,
                genre = movie.genres[0].genre ,
                visitedAt = System.currentTimeMillis()
            )
            Log.d("INSERT" , "insertMovieToWatched: $watchedMovie")
            watchedMovieDao.insert(watchedMovie)
            _isWatched.value = true
        }
    }

    /**
     * Removes movie from watched history.
     *
     * @param id Movie identifier.
     */
    fun deleteMovieFromWatched(id: Int) {
        viewModelScope.launch {
            watchedMovieDao.deleteById(id)
            _isWatched.value = false
            Log.d("DELETE" , "id: $id")
        }
    }
}