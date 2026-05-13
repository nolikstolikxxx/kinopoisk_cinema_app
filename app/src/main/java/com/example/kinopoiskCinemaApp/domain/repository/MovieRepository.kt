package com.example.kinopoiskCinemaApp.domain.repository

import com.example.kinopoiskCinemaApp.data.network.dto.CollectionMovieDto
import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie
import com.example.kinopoiskCinemaApp.domain.model.Images
import com.example.kinopoiskCinemaApp.domain.model.Movie
import com.example.kinopoiskCinemaApp.domain.model.SimilarMovies
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType

interface MovieRepository {

    suspend fun getMovie(id: Int): Movie

    suspend fun getMoviesByCollection(type: MoviesCollectionType): CollectionMovieDto
    suspend fun getMovieById(id: Int): DetailMovie
    suspend fun getActors(filmId: Int): List<Actors>
    //    suspend fun getActorsById(id: Int): Actors
    suspend fun getSimilarMovies(id: Int): SimilarMovies
    suspend fun getImages(id: Int): Images


}