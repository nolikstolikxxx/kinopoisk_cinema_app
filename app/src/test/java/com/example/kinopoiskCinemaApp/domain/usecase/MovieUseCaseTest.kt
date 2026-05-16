package com.example.kinopoiskCinemaApp.domain.usecase

import com.example.kinopoiskCinemaApp.data.network.dto.CollectionMovieDto
import com.example.kinopoiskCinemaApp.domain.model.Country
import com.example.kinopoiskCinemaApp.domain.model.Genre
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.repository.MovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * Unit tests for [MovieUseCase].
 *
 * Tests business logic and interaction
 * with [MovieRepository].
 */
class MovieUseCaseTest {

    // ================================
    // Test dependencies
    // ================================

    private lateinit var movieRepository: MovieRepository
    private lateinit var movieUseCase: MovieUseCase

    // ================================
    // Setup
    // ================================

    @Before
    fun setup() {

        movieRepository = mock(MovieRepository::class.java)

        movieUseCase = MovieUseCase(movieRepository)
    }

    // ================================
    // getFilmsByCollectionType()
    // ================================

    @Test
    fun getFilmsByCollectionType_returnsMoviesList() = runTest {

        // ================================
        // Fake data
        // ================================

        val movies = listOf(
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
                ratingKinopoisk = 8.7 ,
                year = 2014 ,
            )
        )

        val dto = CollectionMovieDto(
            total = 1 ,
            totalPages = 1 ,
            items = movies
        )

        // ================================
        // Mock repository response
        // ================================

        whenever(
            movieRepository.getMoviesByCollection(
                MoviesCollectionType.TOP_250_MOVIES
            )
        ).thenReturn(dto)

        // ================================
        // Execute use case
        // ================================

        val result = movieUseCase.getFilmsByCollectionType(
            MoviesCollectionType.TOP_250_MOVIES
        )

        // ================================
        // Assertions
        // ================================

        assertThat(result).hasSize(1)
        assertThat(result.first().nameRu).isEqualTo("Interstellar")
    }

    // ================================
    // getAllFilms()
    // ================================

    @Test
    fun getAllFilms_returnsMoviesMap() = runTest {

        // ================================
        // Fake movie
        // ================================

        val movies = listOf(
            Movie(
                kinopoiskId = 1 ,
                countries = listOf(Country("USA")) ,
                coverUrl = "url" ,
                description = "" ,
                genres = listOf(Genre("Action")) ,
                logoUrl = "" ,
                nameEn = "" ,
                nameOriginal = "" ,
                nameRu = "Batman" ,
                posterUrl = "url" ,
                posterUrlPreview = "" ,
                ratingAgeLimits = "" ,
                ratingKinopoisk = 8.0 ,
                year = 2022 ,
            )
        )

        val dto = CollectionMovieDto(
            total = 1 ,
            totalPages = 1 ,
            items = movies
        )

        // ================================
        // Mock all enum calls
        // ================================

        MoviesCollectionType.entries.forEach { type ->

            whenever(
                movieRepository.getMoviesByCollection(type)
            ).thenReturn(dto)
        }

        // ================================
        // Execute use case
        // ================================

        val result = movieUseCase.getAllFilms()

        // ================================
        // Assertions
        // ================================

        assertThat(result).isNotEmpty()

        assertThat(
            result[MoviesCollectionType.TOP_250_MOVIES]
        )?.hasSize(1)
    }

    // ================================
    // Error handling
    // ================================

    @Test(expected = RuntimeException::class)
    fun getFilmsByCollectionType_throwsException() = runTest {

        // ================================
        // Mock error
        // ================================

        whenever(
            movieRepository.getMoviesByCollection(
                MoviesCollectionType.TOP_250_MOVIES
            )
        ).thenThrow(RuntimeException("Network error"))

        // ================================
        // Execute use case
        // ================================

        movieUseCase.getFilmsByCollectionType(
            MoviesCollectionType.TOP_250_MOVIES
        )
    }
}