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

class MovieRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi
) : MovieRepository {


    override suspend fun getMovie(id: Int): Movie {
        return api.getMovie(id)
    }

    override suspend fun getMoviesByCollection(
        type: MoviesCollectionType ,
    ): CollectionMovieDto {
        return api.getMoviesByCollection(type)
    }

    override suspend fun getMovieById(
        id: Int,
    ): DetailMovie {
        return api.getMovieById(id)
    }

    override suspend fun getActors(
        filmId: Int,
    ): List<Actors> {
        return api.getActors(filmId)
    }

//    override suspend fun getActorsById(
//        id: Int,
//    ): Actors {
//        return api.getActorsById(id)
//    }

    override suspend fun getSimilarMovies(
        id: Int,
    ): SimilarMovies {
        return api.getSimilarMovies(id)
    }

    override suspend fun getImages(
        id: Int,
    ): Images {
        return api.getImages(id)
    }
}