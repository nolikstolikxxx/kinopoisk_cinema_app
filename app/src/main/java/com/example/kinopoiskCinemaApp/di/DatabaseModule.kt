package com.example.kinopoiskCinemaApp.di

import android.content.Context
import androidx.room.Room
import com.example.kinopoiskCinemaApp.data.db.AppDatabase
import com.example.kinopoiskCinemaApp.domain.dao.WatchedMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module responsible for providing
 * database-related dependencies.
 *
 * Provides:
 * - Room database instance;
 * - DAO instances.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    // ================================
    // Database
    // ================================

    /**
     * Provides singleton instance of Room database.
     *
     * @param context Application context.
     *
     * @return AppDatabase instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context = context ,
            klass = AppDatabase::class.java ,
            name = "cinema.db"
        ).fallbackToDestructiveMigration(false)
            .build()
    }

    // ================================
    // DAO
    // ================================

    /**
     * Provides DAO for watched movies history.
     *
     * @param appDatabase Room database instance.
     *
     * @return WatchedMovieDao instance.
     */
    @Provides
    @Singleton
    fun providesVisitedMovieDao(appDatabase: AppDatabase): WatchedMovieDao {
        return appDatabase.visitHistoryDao()
    }
}