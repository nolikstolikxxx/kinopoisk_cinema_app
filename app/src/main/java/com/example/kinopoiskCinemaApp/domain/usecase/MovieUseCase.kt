package com.example.kinopoiskCinemaApp.domain.usecase

import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){

    suspend fun getFilmById(id: Int): Movie {
        return movieRepository.getMovie(id)
    }

    suspend fun getFilmsByCollectionType(
        moviesCollectionType: MoviesCollectionType
    ) : List<Movie> {
        return movieRepository.getMoviesByCollection(moviesCollectionType).items
    }

    suspend fun getAllFilms() : Map<MoviesCollectionType, List<Movie>> {

        var map =  mutableMapOf<MoviesCollectionType, List<Movie>>()

        for (type in MoviesCollectionType.entries) {

            var movies = getFilmsByCollectionType(type)

            map[type] = movies

        }

        return map
    }

    suspend fun getDetailFilm(
        id: Int
    ): DetailMovie {
        return movieRepository.getMovieById(id)
    }

    suspend fun getActors(
        id: Int,
    ): List<Actors> {
        return movieRepository.getActors(id)
    }

//    suspend fun getActorsById(
//        id: Int,
//    ): Actors {
//        return movieRepository.getActorsById(id)
//    }

    suspend fun getSimilarMovies(
        id: Int,
    ): SimilarMovies {
        return movieRepository.getSimilarMovies(id)
    }

    suspend fun getImages(
        id: Int,
    ): Images {
        return movieRepository.getImages(id)
    }
}