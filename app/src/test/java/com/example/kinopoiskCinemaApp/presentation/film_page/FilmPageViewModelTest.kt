package com.example.kinopoiskCinemaApp.presentation.film_page

import androidx.lifecycle.SavedStateHandle
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao
import com.example.kinopoiskCinemaApp.domain.model.Country
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Genre
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Test class for [FilmPageViewModel].
 *
 * Tests:
 * - movie loading;
 * - watched movie insertion;
 * - watched movie deletion.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FilmPageViewModelTest {

    // ================================
    // Test Dispatcher
    // ================================

    private val testDispatcher = StandardTestDispatcher()

    // ================================
    // Dependencies
    // ================================

    private lateinit var movieUseCase: MovieUseCase

    private lateinit var watchedMovieDao: WatchedMovieDao

    // ================================
    // ViewModel
    // ================================

    private lateinit var viewModel: FilmPageViewModel

    // ================================
    // Setup
    // ================================

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)

        movieUseCase = mockk()

        watchedMovieDao = mockk(relaxed = true)

        /**
         * Empty SavedStateHandle prevents
         * automatic loading inside init{}.
         */
        viewModel = FilmPageViewModel(
            movieUseCase = movieUseCase,
            watchedMovieDao = watchedMovieDao,
            savedStateHandle = SavedStateHandle()
        )
    }

    // ================================
    // Cleanup
    // ================================

    @After
    fun tearDown() {

        Dispatchers.resetMain()
    }

    // ================================
    // Fake Data
    // ================================

    /**
     * Creates fake DetailMovie object
     * for testing.
     */
    private fun createFakeMovie(): DetailMovie {

        return DetailMovie(

            kinopoiskId = 1,
            kinopoiskHDId = "",
            imdbId = "",

            nameRu = "Interstellar",
            nameEn = "Interstellar",
            nameOriginal = "Interstellar",

            posterUrl = "poster",
            posterUrlPreview = "preview",
            coverUrl = "",
            logoUrl = "",

            reviewsCount = 0,

            ratingGoodReview = 0.0,
            ratingGoodReviewVoteCount = 0,

            ratingKinopoisk = 8.9,
            ratingKinopoiskVoteCount = 1000,

            ratingImdb = 8.7,
            ratingImdbVoteCount = 1000,

            ratingFilmCritics = 0.0,
            ratingFilmCriticsVoteCount = 0,

            ratingAwait = 0.0,
            ratingAwaitCount = 0,

            ratingRfCritics = 0.0,
            ratingRfCriticsVoteCount = 0,

            webUrl = "",

            year = 2014,
            filmLength = 169,

            slogan = "",
            description = "",
            shortDescription = "",
            editorAnnotation = "",

            isTicketsAvailable = false,
            productionStatus = "",

            type = "FILM",

            ratingMpaa = "",
            ratingAgeLimits = "",

            hasImax = false,
            has3D = false,

            lastSync = "",

            countries = listOf(
                Country("USA")
            ),

            genres = listOf(
                Genre("Sci-Fi")
            ),

            startYear = 0,
            endYear = 0,

            serial = false,
            shortFilm = false,
            completed = false
        )
    }

    // ================================
    // Success State Tests
    // ================================

    /**
     * Checks:
     *
     * movie loads successfully
     * StateFlow updates correctly
     * loading becomes false
     * error remains empty
     */
    @Test
    fun getMovieById_success_updatesMovieState() = runTest {

        // Arrange

        val fakeMovie = createFakeMovie()

        coEvery {
            movieUseCase.getDetailFilm(1)
        } returns fakeMovie

        coEvery {
            watchedMovieDao.getMovieById(1)
        } returns null

        // Act

        viewModel.getMovieById(1)

        advanceUntilIdle()

        // Assert

        val state = viewModel.stateMovie.value

        assertThat(state.isLoading).isFalse()

        assertThat(state.movie).isEqualTo(fakeMovie)

        assertThat(state.error).isEmpty()

        coVerify(exactly = 1) {
            movieUseCase.getDetailFilm(1)
        }
    }

    // ================================
    // Insert Watched Movie Tests
    // ================================

    /**
     * Checks:
     *
     * watched movie is inserted
     * DAO insert() is called
     * isWatched becomes true
     */
    @Test
    fun insertMovieToWatched_success_updatesWatchedState() = runTest {

        // Arrange

        val fakeMovie = createFakeMovie()

        // Act

        viewModel.insertMovieToWatched(fakeMovie)

        advanceUntilIdle()

        // Assert

        coVerify(exactly = 1) {
            watchedMovieDao.insert(any())
        }

        assertThat(viewModel.isWatched.value).isTrue()
    }

    // ================================
    // Delete Watched Movie Tests
    // ================================

    /**
     * Checks:
     *
     * watched movie is deleted
     * DAO deleteById() is called
     * isWatched becomes false
     */
    @Test
    fun deleteMovieFromWatched_success_updatesWatchedState() = runTest {

        // Act

        viewModel.deleteMovieFromWatched(1)

        advanceUntilIdle()

        // Assert

        coVerify(exactly = 1) {
            watchedMovieDao.deleteById(1)
        }

        assertThat(viewModel.isWatched.value).isFalse()
    }
}