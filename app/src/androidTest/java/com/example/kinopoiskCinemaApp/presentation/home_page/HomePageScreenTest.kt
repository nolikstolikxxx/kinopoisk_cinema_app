package com.example.kinopoiskCinemaApp.presentation.home_page

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.kinopoiskCinemaApp.domain.model.Country
import com.example.kinopoiskCinemaApp.domain.model.Genre
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import org.junit.Rule
import org.junit.Test

/**
 * UI tests for [HomePageScreen].
 *
 * Tests:
 * - loading state;
 * - movies display;
 * - movie items rendering.
 */
class HomePageScreenTest {

    // ================================
    // Compose Rule
    // ================================

    @get:Rule
    val composeRule = createComposeRule()

    // ================================
    // Fake Data
    // ================================

    private fun createFakeMovie(): Movie {

        return Movie(
            kinopoiskId = 1,
            countries = listOf(Country("USA")),
            coverUrl = "",
            description = "",
            genres = listOf(Genre("Sci-Fi")),
            logoUrl = "",
            nameEn = "Interstellar",
            nameOriginal = "Interstellar",
            nameRu = "Интерстеллар",
            posterUrl = "",
            posterUrlPreview = "",
            ratingAgeLimits = "",
            ratingKinopoisk = 8.9,
            year = 2014
        )
    }

    // ================================
    // Movies State Tests
    // ================================

    /**
     * Checks:
     *
     * movie title is displayed
     * movie item exists on screen
     */
    @Test
    fun moviesState_displaysMovieItems() {

        // Arrange

        val fakeMovies = mapOf(
            MoviesCollectionType.TOP_250_MOVIES to listOf(
                createFakeMovie()
            )
        )

        val fakeState = HomePageState(
            isLoading = false,
            movies = fakeMovies,
            error = ""
        )

        // Act

        composeRule.setContent {

            HomePageContent(
                state = fakeState
            )
        }

        // Assert

        composeRule
            .onNodeWithText("Интерстеллар")
            .assertIsDisplayed()

        composeRule
            .onAllNodesWithTag("movie_item")
            .assertCountEquals(1)
    }
}