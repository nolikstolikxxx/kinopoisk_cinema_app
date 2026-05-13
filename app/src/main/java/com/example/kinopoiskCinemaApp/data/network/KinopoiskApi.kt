package com.example.kinopoiskCinemaApp.data.network

import com.example.kinopoiskCinemaApp.common.Constants
import com.example.kinopoiskCinemaApp.data.network.dto.CollectionMovieDto
import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for interacting
 * with Kinopoisk API endpoints.
 *
 * Provides methods for:
 * - Loading movies collections;
 * - Loading movie details;
 * - Loading actors and staff information;
 * - Loading similar movies;
 * - Loading movie images.
 */
interface KinopoiskApi {

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
    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getMovie(
        @Path("id") id: Int ,
    ): Movie

    /**
     * Loads movie collections by collection type.
     *
     * @param type Collection type.
     * @param page Collection page number.
     *
     * @return CollectionMovieDto containing movies list.
     */
    @GET("/api/v2.2/films/collections")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getMoviesByCollection(
        @Query("type") type: MoviesCollectionType ,
        @Query("page") page: Int = 1
    ): CollectionMovieDto

    /**
     * Loads detailed movie information by ID.
     *
     * @param id Movie identifier.
     *
     * @return DetailMovie object.
     */
    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getMovieById(
        @Path("id") id: Int ,
    ): DetailMovie

    // ================================
    // Actors & Staff
    // ================================

    /**
     * Loads actors list for a specific movie.
     *
     * @param filmId Movie identifier.
     *
     * @return List of actors.
     */
    @GET("/api/v1/staff")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getActors(
        @Query("filmId") filmId: Int ,
    ): List<Actors>

    /**
     * Loads detailed information about staff member.
     *
     * @param id Staff member identifier.
     *
     * @return Staff details object.
     */
    @GET("/api/v1/staff/{id}")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getStaffDetails(
        @Path("id") id: Int ,
    ): Staff

    // ================================
    // Similar Movies
    // ================================

    /**
     * Loads movies similar to the selected movie.
     *
     * @param id Movie identifier.
     *
     * @return SimilarMovies object.
     */
    @GET("/api/v2.2/films/{id}/similars")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getSimilarMovies(
        @Path("id") id: Int ,
    ): SimilarMovies

    // ================================
    // Images
    // ================================

    /**
     * Loads images related to the selected movie.
     *
     * @param id Movie identifier.
     *
     * @return Images object.
     */
    @GET("/api/v2.2/films/{id}/images")
    @Headers("X-API-KEY: ${Constants.APIKEY}")
    suspend fun getImages(
        @Path("id") id: Int ,
    ): Images

}