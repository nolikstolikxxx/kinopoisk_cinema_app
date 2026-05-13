package com.example.kinopoiskCinemaApp.presentation.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoiskCinemaApp.R
import com.example.kinopoiskCinemaApp.presentation.profile.components.DeleteItem
import com.example.kinopoiskCinemaApp.presentation.profile.components.WatchedMovieItem
import com.example.kinopoiskCinemaApp.presentation.profile.viewmodels.ProfileViewModel

/**
 * Profile screen displaying recently watched movies.
 *
 * Features:
 * - Displaying watched movies history;
 * - Navigation to full watched movies screen;
 * - Navigation to movie details screen;
 * - Clearing watched history.
 */
@Composable
fun ProfileScreen(

    /**
     * Navigation controller used for screen navigation.
     */
    navController: NavController ,

    /**
     * ViewModel responsible for profile state management.
     */
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    // ================================
    // State
    // ================================

    /**
     * Collected UI state containing watched movies data.
     */
    val watchedFilmsState by profileViewModel.watchedMoviesState.collectAsState()

    // ================================
    // Initial Data Loading
    // ================================

    /**
     * Loads watched movies when screen appears.
     */
    LaunchedEffect(Unit) {
        profileViewModel.getWatchedMovies()
    }

    // ================================
    // Screen Content
    // ================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 26.dp)
        ) {

            // Display content only if there is no loading or error state
            if (!watchedFilmsState.isLoading && watchedFilmsState.error.isBlank()) {

                // ================================
                // Header Section
                // ================================

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .padding(end = 24.dp) ,

                    verticalAlignment = Alignment.CenterVertically ,
                    horizontalArrangement = Arrangement.SpaceBetween ,
                ) {

                    /**
                     * Section title.
                     */
                    Text(
                        text = "Просмотрено" ,
                        style = TextStyle(
                            fontWeight = FontWeight.W600 ,
                            fontSize = 18.sp ,
                            lineHeight = 19.8.sp

                        ) ,
                    )

                    /**
                     * Button for navigating
                     * to the full watched movies screen.
                     */
                    TextButton(
                        onClick = {
                            navController.navigate("watchedMovies")
                        }
                    ) {
                        Text(
                            text = watchedFilmsState.movies.size.toString() ,
                            style = TextStyle(fontSize = 15.sp) ,
                            color = Color(0xFF3D3BFF) ,
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_right) ,
                            contentDescription = null ,
                            tint = Color(0xFF3D3BFF) ,
                            modifier = Modifier
                                .size(10.dp)
                                .padding(start = 4.dp)
                        )
                    }
                }

                // ================================
                // Watched Movies List
                // ================================

                if (watchedFilmsState.movies.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier.padding(bottom = 16.dp) ,
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {

                        /**
                         * Display watched movies items.
                         */
                        items(watchedFilmsState.movies) { mov ->
                            WatchedMovieItem(
                                movie = mov ,

                                /**
                                 * Navigate to movie details screen.
                                 */
                                onItemClick = {
                                    navController.navigate(
                                        "detailMovie/${mov.movieId}"
                                    )
                                }
                            )
                        }

                        /**
                         * Item for clearing watched history.
                         */
                        item {
                            DeleteItem(
                                onItemClick = { profileViewModel.clearHistory() }
                            )
                        }
                    }
                }
            }
        }
    }
}