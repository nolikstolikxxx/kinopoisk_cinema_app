package com.example.kinopoiskCinemaApp.di

import com.example.kinopoiskCinemaApp.common.Constants
import com.example.kinopoiskCinemaApp.data.network.KinopoiskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module responsible for providing
 * network-related dependencies.
 *
 * Provides:
 * - Retrofit instance;
 * - Kinopoisk API service.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // ================================
    // Retrofit
    // ================================

    /**
     * Provides singleton Retrofit instance.
     *
     * Configures:
     * - Base URL;
     * - Gson converter factory.
     *
     * @return Configured Retrofit instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // ================================
    // API Service
    // ================================

    /**
     * Provides Kinopoisk API service implementation.
     *
     * @param retrofit Retrofit instance.
     *
     * @return KinopoiskApi implementation.
     */
    @Provides
    @Singleton
    fun provideKinopoiskApi(retrofit: Retrofit): KinopoiskApi {
        return retrofit.create(KinopoiskApi::class.java)
    }
}