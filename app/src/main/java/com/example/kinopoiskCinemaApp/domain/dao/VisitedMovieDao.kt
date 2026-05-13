package com.example.kinopoiskCinemaApp.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie

@Dao
interface WatchedMovieDao {

    @Query("select * from watched_movies where watched_movies.movieId = :id")
    suspend fun getMovieById(id: Int): WatchedMovie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(watchedMovie: WatchedMovie)

    @Query("select * from watched_movies order by watched_movies.visitedAt desc limit 7")
    suspend fun getRecentVisitedMovies(): List<WatchedMovie>

    @Query("select * from watched_movies")
    suspend fun getAllVisitedMovies(): List<WatchedMovie>

    @Query("delete from watched_movies")
    suspend fun clear()

    @Query("delete from watched_movies where watched_movies.movieId = :id")
    suspend fun deleteById(id: Int)

}