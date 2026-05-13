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

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = "cinema.db"
            ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun providesVisitedMovieDao(appDatabase: AppDatabase): WatchedMovieDao {
        return appDatabase.visitHistoryDao()
    }


}