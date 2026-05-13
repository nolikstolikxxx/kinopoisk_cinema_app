package com.example.kinopoiskCinemaApp.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie

/**
 * Room DAO interface for managing
 * watched movies history.
 *
 * Provides methods for:
 * - Inserting watched movies;
 * - Retrieving watched history;
 * - Deleting watched movies;
 * - Clearing history.
 */
@Dao
interface WatchedMovieDao {

    // ================================
    // Get Movie By ID
    // ================================

    /**
     * Returns watched movie by movie ID.
     *
     * @param id Movie identifier.
     *
     * @return WatchedMovie object or null.
     */
    @Query("select * from watched_movies where watched_movies.movieId = :id")
    suspend fun getMovieById(id: Int): WatchedMovie?

    // ================================
    // Insert Movie
    // ================================

    /**
     * Inserts watched movie into database.
     *
     * Replaces existing movie if conflict occurs.
     *
     * @param watchedMovie Movie entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(watchedMovie: WatchedMovie)

    // ================================
    // Recent Movies
    // ================================

    /**
     * Returns recently watched movies.
     *
     * Sorted by visit date in descending order.
     * Limited to last 7 movies.
     *
     * @return List of watched movies.
     */
    @Query("select * from watched_movies order by watched_movies.visitedAt desc limit 7")
    suspend fun getRecentVisitedMovies(): List<WatchedMovie>

    // ================================
    // All Movies
    // ================================

    /**
     * Returns all watched movies history.
     *
     * @return List of watched movies.
     */
    @Query("select * from watched_movies")
    suspend fun getAllVisitedMovies(): List<WatchedMovie>

    // ================================
    // Clear History
    // ================================

    /**
     * Removes all watched movies
     * from the database.
     */
    @Query("delete from watched_movies")
    suspend fun clear()

    // ================================
    // Delete Movie By ID
    // ================================

    /**
     * Deletes watched movie by movie ID.
     *
     * @param id Movie identifier.
     */
    @Query("delete from watched_movies where watched_movies.movieId = :id")
    suspend fun deleteById(id: Int)

}