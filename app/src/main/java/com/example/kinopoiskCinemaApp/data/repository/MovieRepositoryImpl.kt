package com.example.kinopoiskCinemaApp.data.repository

import com.example.kinopoiskCinemaApp.data.network.KinopoiskApi
import com.example.kinopoiskCinemaApp.data.network.dto.CollectionMovieDto
import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Repository implementation responsible for
 * movie-related data operations.
 *
 * Works with:
 * - Movies collections;
 * - Movie details;
 * - Actors;
 * - Similar movies;
 * - Movie images.
 */
class MovieRepositoryImpl @Inject constructor(

    /**
     * Retrofit API service.
     */
    private val api: KinopoiskApi

) : MovieRepository {

    // ================================
    // Movies
    // ================================

    /**
     * Loads short movie information by ID.
     *
     * @param id Movie identifier.
     *
     * @return Movie object.
     */
    override suspend fun getMovie(id: Int): Movie {

        return api.getMovie(id)
    }

    /**
     * Loads movies collection by collection type.
     *
     * @param type Movies collection type.
     *
     * @return CollectionMovieDto object.
     */
    override suspend fun getMoviesByCollection(
        type: MoviesCollectionType ,
    ): CollectionMovieDto {

        return api.getMoviesByCollection(type)

    }

    /**
     * Loads detailed movie information.
     *
     * @param id Movie identifier.
     *
     * @return DetailMovie object.
     */
    override suspend fun getMovieById(
        id: Int ,
    ): DetailMovie {

        return api.getMovieById(id)
    }

    // ================================
    // Actors
    // ================================

    /**
     * Loads actors list for selected movie.
     *
     * @param filmId Movie identifier.
     *
     * @return List of actors.
     */
    override suspend fun getActors(
        filmId: Int ,
    ): List<Actors> {

        return api.getActors(filmId)
    }

    /**
     * Loads actor details by ID.
     *
     * @param id Actor identifier.
     *
     * @return Actors object.
     */
    override suspend fun getActorsById(
        id: Int,
    ): Actors {
        return api.getActorsById(id)
    }

    // ================================
    // Similar Movies
    // ================================

    /**
     * Loads movies similar to selected movie.
     *
     * @param id Movie identifier.
     *
     * @return SimilarMovies object.
     */
    override suspend fun getSimilarMovies(
        id: Int ,
    ): SimilarMovies {

        return api.getSimilarMovies(id)
    }

    // ================================
    // Images
    // ================================

    /**
     * Loads images related to selected movie.
     *
     * @param id Movie identifier.
     *
     * @return Images object.
     */
    override suspend fun getImages(
        id: Int ,
    ): Images {
        return api.getImages(id)
    }
}