package com.example.kinopoiskCinemaApp.di

import com.example.kinopoiskCinemaApp.data.network.KinopoiskApi
import com.example.kinopoiskCinemaApp.data.repository.MovieRepositoryImpl
import com.example.kinopoiskCinemaApp.data.repository.StaffRepositoryImpl
import com.example.kinopoiskCinemaApp.domain.repository.MovieRepository
import com.example.kinopoiskCinemaApp.domain.repository.StaffRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        api: KinopoiskApi
    ): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesStaffRepository(
        api: KinopoiskApi
    ): StaffRepository {
        return StaffRepositoryImpl(api)
    }
}