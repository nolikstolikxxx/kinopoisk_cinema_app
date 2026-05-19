package com.example.kinopoiskCinemaApp.presentation.home_page

import com.example.kinopoiskCinemaApp.domain.model.Country
import com.example.kinopoiskCinemaApp.domain.model.Genre
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
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
 * Test class for [HomePageViewModel].
 *
 * Tests:
 * - success state;
 * - error handling.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class HomePageViewModelTest {

    // ================================
    // Test Dispatcher
    // ================================

    private val testDispatcher = StandardTestDispatcher()

    // ================================
    // Dependencies
    // ================================

    private lateinit var movieUseCase: MovieUseCase

    private lateinit var viewModel: HomePageViewModel

    // ================================
    // Setup
    // ================================

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)

        movieUseCase = mockk()
    }

    // ================================
    // Cleanup
    // ================================

    @After
    fun tearDown() {

        Dispatchers.resetMain()
    }

    // ================================
    // Success State Tests
    // ================================

    /**
     * Checks:
     *
     * ViewModel calls UseCase
     * data is loaded successfully
     * StateFlow is updated
     * loading is disabled
     * error is empty
     */
    @Test
    fun getAllMovies_success_updatesStateWithMovies() = runTest {

        // Arrange

        val fakeMovies = mapOf(
            MoviesCollectionType.TOP_250_MOVIES to listOf(
                Movie(
                    kinopoiskId = 1 ,
                    countries = listOf(Country("USA")) ,
                    coverUrl = "url" ,
                    description = "" ,
                    genres = listOf(Genre("Sci-Fi")) ,
                    logoUrl = "" ,
                    nameEn = "" ,
                    nameOriginal = "" ,
                    nameRu = "Interstellar" ,
                    posterUrl = "url" ,
                    posterUrlPreview = "url" ,
                    ratingAgeLimits = "" ,
                    ratingKinopoisk = 8.9 ,
                    year = 2014 ,
                )
            )
        )

        coEvery {
            movieUseCase.getAllFilms()
        } returns fakeMovies

        // Act

        viewModel = HomePageViewModel(movieUseCase)

        advanceUntilIdle()

        // Assert

        val state = viewModel.state.value

        assertThat(state.isLoading).isFalse()
        assertThat(state.movies).isEqualTo(fakeMovies)
        assertThat(state.error).isEmpty()
    }

    // ================================
    // Error State Tests
    // ================================

    /**
     * Checks:
     *
     * Errors are handled correctly
     * The application does not crash
     * The state contains an error
     * Loading is disabled after an error
     */
    @Test
    fun getAllMovies_error_updatesStateWithError() = runTest {

        // Arrange

        coEvery {
            movieUseCase.getAllFilms()
        } throws RuntimeException("Network error")

        // Act

        viewModel = HomePageViewModel(movieUseCase)

        advanceUntilIdle()

        // Assert

        val state = viewModel.state.value

        assertThat(state.isLoading).isFalse()
        assertThat(state.movies).isEmpty()
        assertThat(state.error).isNotEmpty()
    }
}