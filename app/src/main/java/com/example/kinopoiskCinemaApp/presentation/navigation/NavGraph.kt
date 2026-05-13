package com.example.kinopoiskCinemaApp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kinopoiskCinemaApp.presentation.film_page.FilmPageScreen
import com.example.kinopoiskCinemaApp.presentation.gallery.GalleryPageScreen
import com.example.kinopoiskCinemaApp.presentation.home_page.HomePageScreen
import com.example.kinopoiskCinemaApp.presentation.movie_collection.MovieCollectionScreen
import com.example.kinopoiskCinemaApp.presentation.profile.screens.ProfileScreen
import com.example.kinopoiskCinemaApp.presentation.profile.screens.WatchedMoviesScreen
import com.example.kinopoiskCinemaApp.presentation.staff_detail.StaffDetailScreen
import com.example.kinopoiskCinemaApp.presentation.staff_detail_movie.StaffDetailMoviesScreen
import com.example.kinopoiskCinemaApp.presentation.staff_filmography.StaffFilmographyScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController, startDestination = "homepage"
    ) { //todo: change to constants
        composable("homepage") {
            HomePageScreen(navController = navHostController)
        }

        composable("search") {
            Search()
        }

        composable("profile") {
            ProfileScreen(
                navController = navHostController
            )
        }

        composable ( "watchedMovies" ) {
            WatchedMoviesScreen(
                navController = navHostController
            )
        }

        composable("movieCollection/{type}") { backStackEntry ->
            MovieCollectionScreen(navController = navHostController)
        }

        composable("staffDetails/{staffId}") {
            StaffDetailScreen(navController = navHostController)
        }

        composable("staffDetailsMovies/{staffId}") {
            StaffDetailMoviesScreen(navController = navHostController)
        }

        composable("staffFilmography/{staffId}") {
            StaffFilmographyScreen(navController = navHostController)
        }

        composable("detailMovie/{id}"){
            FilmPageScreen(navController = navHostController)
        }

        composable("galleryPage/{id}"){
            GalleryPageScreen(navController = navHostController)
        }

    }
}