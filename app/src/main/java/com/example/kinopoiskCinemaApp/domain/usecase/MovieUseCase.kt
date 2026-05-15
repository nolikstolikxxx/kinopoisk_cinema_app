package com.example.kinopoiskCinemaApp.domain.usecase

import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * UseCase responsible for movie-related business logic.
 *
 * Handles:
 * - Loading movies collections;
 * - Loading detailed movie information;
 * - Loading actors;
 * - Loading similar movies;
 * - Loading images.
 */
class MovieUseCase @Inject constructor(

    /**
     * Repository for movie data operations.
     */
    private val movieRepository: MovieRepository

) {

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
    suspend fun getFilmById(id: Int): Movie {
        return movieRepository.getMovie(id)
    }

    /**
     * Loads movies by collection type.
     *
     * @param moviesCollectionType Collection type.
     *
     * @return List of movies.
     */
    suspend fun getFilmsByCollectionType(
        moviesCollectionType: MoviesCollectionType
    ): List<Movie> {

        return movieRepository
            .getMoviesByCollection(moviesCollectionType)
            .items
    }

    /**
     * Loads all available movie collections.
     *
     * Iterates through all collection types
     * and creates map of movies.
     *
     * @return Map of collection types and movies lists.
     */
    suspend fun getAllFilms():
            Map<MoviesCollectionType , List<Movie>> {

        val map = mutableMapOf<MoviesCollectionType , List<Movie>>()

        for (type in MoviesCollectionType.entries) {

            val movies = getFilmsByCollectionType(type)
            map[type] = movies

        }

        return map
    }

    /**
     * Loads detailed movie information.
     *
     * @param id Movie identifier.
     *
     * @return DetailMovie object.
     */
    suspend fun getDetailFilm(
        id: Int
    ): DetailMovie {
        return movieRepository.getMovieById(id)
    }

    // ================================
    // Actors
    // ================================

    /**
     * Loads actors list for selected movie.
     *
     * @param id Movie identifier.
     *
     * @return List of actors.
     */
    suspend fun getActors(
        id: Int ,
    ): List<Actors> {
        return movieRepository.getActors(id)
    }

    /**
     * Loads actor details by ID.
     *
     * @param id Actor identifier.
     *
     * @return Actors object.
     */
    suspend fun getActorsById(
        id: Int ,
    ): Actors {
        return movieRepository.getActorsById(id)
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
    suspend fun getSimilarMovies(
        id: Int ,
    ): SimilarMovies {
        return movieRepository.getSimilarMovies(id)
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
    suspend fun getImages(
        id: Int ,
    ): Images {
        return movieRepository.getImages(id)
    }
}