package com.example.kinopoiskCinemaApp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kinopoiskCinemaApp.data.entities.WatchedMovie
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao

@Database(
    entities = [WatchedMovie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun visitHistoryDao(): WatchedMovieDao
}