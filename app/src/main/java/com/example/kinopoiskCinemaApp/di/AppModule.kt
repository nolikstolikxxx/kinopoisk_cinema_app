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

/**
 * Hilt module responsible for providing
 * application-level dependencies.
 *
 * Provides:
 * - MovieRepository;
 * - StaffRepository.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // ================================
    // Movie Repository
    // ================================

    /**
     * Provides MovieRepository implementation.
     *
     * @param api Retrofit API service.
     *
     * @return MovieRepository implementation.
     */
    @Provides
    @Singleton
    fun providesMovieRepository(
        api: KinopoiskApi
    ): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    // ================================
    // Staff Repository
    // ================================

    /**
     * Provides StaffRepository implementation.
     *
     * @param api Retrofit API service.
     *
     * @return StaffRepository implementation.
     */
    @Provides
    @Singleton
    fun providesStaffRepository(
        api: KinopoiskApi
    ): StaffRepository {
        return StaffRepositoryImpl(api)
    }
}