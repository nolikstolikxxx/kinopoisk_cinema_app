package com.example.kinopoiskCinemaApp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao

/**
 * Main Room database of the application.
 *
 * Contains:
 * - Watched movies history table;
 * - DAO access methods.
 */
@Database(
    entities = [WatchedMovie::class] ,
    version = 1 ,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // ================================
    // DAO Access
    // ================================

    /**
     * Provides access to watched movies DAO.
     *
     * @return WatchedMovieDao instance.
     */
    abstract fun visitHistoryDao(): WatchedMovieDao
}