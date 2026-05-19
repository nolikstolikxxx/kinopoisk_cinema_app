package com.example.kinopoiskCinemaApp.presentation.staff_filmography

import androidx.lifecycle.SavedStateHandle
import com.example.kinopoiskCinemaApp.domain.model.Country
import com.example.kinopoiskCinemaApp.domain.model.Film
import com.example.kinopoiskCinemaApp.domain.model.Genre
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.model.enums.ProfessionKey
import com.example.kinopoiskCinemaApp.domain.usecase.MovieUseCase
import com.example.kinopoiskCinemaApp.domain.usecase.StaffUseCase
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
 * Test class for [StaffFilmographyViewModel].
 *
 * Tests:
 * - profession type changes;
 * - movie grouping by ProfessionKey;
 * - state updates.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class StaffFilmographyViewModelTest {

    // ================================
    // Test Dispatcher
    // ================================

    private val testDispatcher = StandardTestDispatcher()

    // ================================
    // Dependencies
    // ================================

    private lateinit var movieUseCase: MovieUseCase

    private lateinit var staffUseCase: StaffUseCase

    // ================================
    // ViewModel
    // ================================

    private lateinit var viewModel: StaffFilmographyViewModel

    // ================================
    // Setup
    // ================================

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher)

        movieUseCase = mockk()

        staffUseCase = mockk()
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
     * Creates fake Movie object.
     */
    private fun createFakeMovie(
        id: Int,
        name: String
    ): Movie {

        return Movie(
            kinopoiskId = id,
            countries = listOf(Country("USA")),
            coverUrl = "",
            description = "",
            genres = listOf(Genre("Drama")),
            logoUrl = "",
            nameEn = name,
            nameOriginal = name,
            nameRu = name,
            posterUrl = "",
            posterUrlPreview = "",
            ratingAgeLimits = "",
            ratingKinopoisk = 8.0,
            year = 2020
        )
    }

    // ================================
    // Profession Type Tests
    // ================================

    /**
     * Checks:
     *
     * professionKey changes correctly
     * state updates successfully
     */
    @Test
    fun changeFilmographyType_updatesProfessionKey() {

        // Arrange

        viewModel = StaffFilmographyViewModel(
            movieUseCase = movieUseCase,
            staffUseCase = staffUseCase,
            savedStateHandle = SavedStateHandle()
        )

        // Act

        viewModel.changeFilmographyType(
            ProfessionKey.DIRECTOR
        )

        // Assert

        assertThat(
            viewModel.state.value.professionKey
        ).isEqualTo(
            ProfessionKey.DIRECTOR
        )
    }

    // ================================
    // Grouping Tests
    // ================================

    /**
     * Checks:
     *
     * movies are grouped correctly
     * by ProfessionKey.
     */
    @Test
    fun getStaffFilmography_groupsMoviesByProfession() = runTest {

        // Arrange

        val actorMovie = createFakeMovie(
            id = 1,
            name = "Interstellar"
        )

        val directorMovie = createFakeMovie(
            id = 2,
            name = "Inception"
        )

        val fakeStaff = Staff(

            age = 50,
            birthday = "",
            birthPlace = "",
            death = "",
            deathPlace = "",
            facts = emptyList(),

            films = listOf(

                Film(
                    description = "",
                    filmId = 1,
                    general = false,
                    nameEn = "",
                    nameRu = "",
                    professionKey = "ACTOR",
                    rating = ""
                ),

                Film(
                    description = "",
                    filmId = 2,
                    general = false,
                    nameEn = "",
                    nameRu = "",
                    professionKey = "DIRECTOR",
                    rating = ""
                )
            ),

            growth = "",
            hasAwards = 0,
            nameEn = "",
            nameRu = "Christopher Nolan",
            personId = 1,
            posterUrl = "",
            profession = "",
            sex = "",
            webUrl = ""
        )

        coEvery {
            staffUseCase.getStuffDetailsById(1)
        } returns fakeStaff

        coEvery {
            movieUseCase.getFilmById(1)
        } returns actorMovie

        coEvery {
            movieUseCase.getFilmById(2)
        } returns directorMovie

        // Act

        viewModel = StaffFilmographyViewModel(
            movieUseCase = movieUseCase,
            staffUseCase = staffUseCase,
            savedStateHandle = SavedStateHandle(
                mapOf("staffId" to "1")
            )
        )

        advanceUntilIdle()

        // Assert

        val state = viewModel.state.value

        assertThat(state.isLoading).isFalse()

        assertThat(
            state.movies[ProfessionKey.ACTOR]
        )?.containsExactly(actorMovie)

        assertThat(
            state.movies[ProfessionKey.DIRECTOR]
        )?.containsExactly(directorMovie)

        assertThat(state.staffName)
            .isEqualTo("Christopher Nolan")

        assertThat(state.error).isEmpty()
    }
}