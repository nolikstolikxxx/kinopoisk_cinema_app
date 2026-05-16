package com.example.kinopoiskCinemaApp.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test class for [WatchedMovieDao].
 *
 * Tests:
 * - insert operations;
 * - delete operations;
 * - query operations.
 */
@RunWith(AndroidJUnit4::class)
class WatchedMovieDaoTest {
    // ================================
    // Database
    // ================================

    private lateinit var database: AppDatabase

    private lateinit var dao: WatchedMovieDao

    // ================================
    // Setup
    // ================================

    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext() ,
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = database.visitHistoryDao()
    }

    // ================================
    // Cleanup
    // ================================

    @After
    fun teardown() {

        database.close()
    }

    // ================================
    // Insert Tests
    // ================================

    /**
     * Checks:
     *
     * insert works
     * movie is saved
     * query returns an object
     */
    @Test
    fun insertMovie_movieSavedSuccessfully() = runTest {

        val movie = WatchedMovie(
            movieId = 1 ,
            posterUrl = "poster_url" ,
            name = "Interstellar" ,
            rating = 8.9 ,
            genre = "Sci-Fi" ,
            visitedAt = 1000L
        )

        dao.insert(movie)

        val result = dao.getMovieById(1)

        assertThat(result).isNotNull()
        assertThat(result?.name).isEqualTo("Interstellar")
    }

    // ================================
    // Delete Tests
    // ================================

    /**
     * Checks:
     *
     * deleteById works
     */
    @Test
    fun deleteMovie_movieDeletedSuccessfully() = runTest {

        val movie = WatchedMovie(
            movieId = 1 ,
            posterUrl = "poster_url" ,
            name = "Interstellar" ,
            rating = 8.9 ,
            genre = "Sci-Fi" ,
            visitedAt = 1000L
        )

        dao.insert(movie)

        dao.deleteById(1)

        val result = dao.getMovieById(1)

        assertThat(result).isNull()
    }

    // ================================
    // Query Tests
    // ================================

    /**
     * Checks:
     *
     * LIMIT 7 is working
     */
    @Test
    fun getRecentVisitedMovies_returnsOnly7Movies() = runTest {

        repeat(10) { index ->

            dao.insert(
                WatchedMovie(
                    movieId = index ,
                    posterUrl = "url" ,
                    name = "Movie $index" ,
                    rating = 8.0 ,
                    genre = "Action" ,
                    visitedAt = index.toLong()
                )
            )
        }

        val result = dao.getRecentVisitedMovies()

        assertThat(result.size).isEqualTo(7)
    }

    /**
     * Checks:
     *
     * clear clears the table
     */
    @Test
    fun clear_allMoviesDeleted() = runTest {

        dao.insert(
            WatchedMovie(
                movieId = 1 ,
                posterUrl = "url" ,
                name = "Movie" ,
                rating = 8.0 ,
                genre = "Drama" ,
                visitedAt = 1L
            )
        )

        dao.clear()

        val result = dao.getAllVisitedMovies()

        assertThat(result).isEmpty()
    }
}