package com.example.kinopoiskCinemaApp.presentation.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.example.kinopoiskCinemaApp.presentation.profile.components.WatchedMovieItem
import com.example.kinopoiskCinemaApp.presentation.profile.viewmodels.WatchedMoviesViewModel

/**
 * Screen displaying the full watched movies history.
 *
 * Features:
 * - Displaying watched movies grid;
 * - Loading and error states;
 * - Navigation to movie details screen;
 * - Clearing watched history;
 * - Returning back to previous screen.
 */
@Composable
fun WatchedMoviesScreen(

    /**
     * Navigation controller used for screen navigation.
     */
    navController: NavController ,

    /**
     * ViewModel responsible for watched movies state management.
     */
    watchedMoviesViewModel: WatchedMoviesViewModel = hiltViewModel()
) {

    // ================================
    // State
    // ================================

    /**
     * Collected UI state for watched movies.
     */
    val state by watchedMoviesViewModel.watchedMoviesState.collectAsState()

    // ================================
    // Screen Content
    // ================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ================================
        // Loading State
        // ================================

        if (state.isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // ================================
        // Error State
        // ================================

        else if (state.error.isNotBlank()) {

            Text(
                text = state.error ,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        }

        // ================================
        // Content State
        // ================================

        else {

            Column {

                // ================================
                // Top Bar
                // ================================
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    /**
                     * Back navigation button.
                     */
                    IconButton(
                        onClick = { navController.popBackStack() } ,
                        modifier = Modifier
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back) ,
                            contentDescription = "Back icon" ,
                        )
                    }

                    /**
                     * Screen title.
                     */
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center) ,
                        text = "Просмотрено" ,
                        style = TextStyle(
                            fontWeight = FontWeight.W600 ,
                            fontSize = 14.sp ,
                            lineHeight = 13.2.sp ,
                            color = Color(0xFF272727)
                        )
                    )

                    /**
                     * Button for clearing watched history.
                     */
                    TextButton(
                        modifier = Modifier
                            .align(Alignment.CenterEnd) ,
                        onClick = { watchedMoviesViewModel.clear() }
                    ) {
                        Text(
                            text = "Очистить историю" ,
                            style = TextStyle(fontSize = 12.sp) ,
                            color = Color(0xFF3D3BFF) ,
                        )
                    }
                }

                // ================================
                // Watched Movies Grid
                // ================================

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2) ,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            start = 61.dp ,
                            end = 61.dp
                        ) ,
                    horizontalArrangement = Arrangement.Center ,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    /**
                     * Display watched movies items.
                     */
                    items(state.movies) { movie ->
                        WatchedMovieItem(
                            movie = movie ,

                            /**
                             * Navigate to movie details screen.
                             */
                            onItemClick = {
                                navController.navigate(
                                    "detailMovie/${movie.movieId}}"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}