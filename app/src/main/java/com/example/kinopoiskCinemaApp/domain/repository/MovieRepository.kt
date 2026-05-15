package com.example.kinopoiskCinemaApp.domain.repository

import com.example.kinopoiskCinemaApp.data.network.dto.CollectionMovieDto
import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType

/**
 * Repository interface responsible for
 * movie-related operations.
 *
 * Provides methods for:
 * - Loading movies collections;
 * - Loading movie details;
 * - Loading actors;
 * - Loading similar movies;
 * - Loading movie images.
 */
interface MovieRepository {

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
    suspend fun getMovie(id: Int): Movie

    /**
     * Loads movies collection by collection type.
     *
     * @param type Movies collection type.
     *
     * @return CollectionMovieDto object.
     */
    suspend fun getMoviesByCollection(type: MoviesCollectionType): CollectionMovieDto

    /**
     * Loads detailed movie information.
     *
     * @param id Movie identifier.
     *
     * @return DetailMovie object.
     */
    suspend fun getMovieById(id: Int): DetailMovie

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
    suspend fun getActors(filmId: Int): List<Actors>

    /**
     * Loads actor details by ID.
     *
     * @param id Actor identifier.
     *
     * @return Actors object.
     */
    suspend fun getActorsById(id: Int): Actors

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
    suspend fun getSimilarMovies(id: Int): SimilarMovies

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
    suspend fun getImages(id: Int): Images
}