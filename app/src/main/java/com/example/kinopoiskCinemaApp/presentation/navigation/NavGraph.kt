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

/**
 * Main navigation graph of the application.
 *
 * Contains all application destinations
 * and navigation routes.
 */
@Composable
fun NavGraph(

    /**
     * Main navigation controller.
     */
    navHostController: NavHostController
) {
    // ================================
    // Navigation Host
    // ================================

    NavHost(
        navController = navHostController ,
        /**
         * Start destination of the application.
         */
        startDestination = NavigationRoutes.HOME
    ) {

        // ================================
        // Home Screen
        // ================================

        composable(NavigationRoutes.HOME) {
            HomePageScreen(navController = navHostController)
        }

        // ================================
        // Search Screen
        // ================================

        composable(NavigationRoutes.SEARCH) {
            Search()
        }

        // ================================
        // Profile Screen
        // ================================

        composable(NavigationRoutes.PROFILE) {
            ProfileScreen(
                navController = navHostController
            )
        }

        // ================================
        // Watched Movies Screen
        // ================================

        composable(NavigationRoutes.WATCHED_MOVIES) {
            WatchedMoviesScreen(
                navController = navHostController
            )
        }

        // ================================
        // Movie Collection Screen
        // ================================

        composable(NavigationRoutes.MOVIE_COLLECTION) {
            MovieCollectionScreen(navController = navHostController)
        }

        // ================================
        // Staff Details Screen
        // ================================

        composable(NavigationRoutes.STAFF_DETAILS) {
            StaffDetailScreen(navController = navHostController)
        }

        // ================================
        // Staff Movies Screen
        // ================================

        composable(NavigationRoutes.STAFF_DETAILS_MOVIES) {
            StaffDetailMoviesScreen(navController = navHostController)
        }

        // ================================
        // Staff Filmography Screen
        // ================================

        composable(NavigationRoutes.STAFF_FILMOGRAPHY) {
            StaffFilmographyScreen(navController = navHostController)
        }

        // ================================
        // Movie Details Screen
        // ================================

        composable(NavigationRoutes.DETAIL_MOVIE) {
            FilmPageScreen(navController = navHostController)
        }

        // ================================
        // Gallery Screen
        // ================================

        composable(NavigationRoutes.GALLERY_PAGE) {
            GalleryPageScreen(navController = navHostController)
        }
    }
}