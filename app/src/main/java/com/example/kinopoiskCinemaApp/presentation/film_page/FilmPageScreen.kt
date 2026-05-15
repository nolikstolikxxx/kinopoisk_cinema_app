package com.example.kinopoiskCinemaApp.presentation.film_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoiskCinemaApp.R
import com.example.kinopoiskCinemaApp.domain.model.Actors
import com.example.kinopoiskCinemaApp.presentation.film_page.components.ActorsList
import com.example.kinopoiskCinemaApp.presentation.film_page.components.DetailMovieItem
import com.example.kinopoiskCinemaApp.presentation.film_page.components.GalleryListItem
import com.example.kinopoiskCinemaApp.presentation.film_page.components.MovieDescriptionItem
import com.example.kinopoiskCinemaApp.presentation.film_page.components.SimilarFilmsListItem

@Composable
fun FilmPageScreen(
    navController: NavController ,
    movieViewModel: FilmPageViewModel = hiltViewModel() ,
) {
    val stateMovie by movieViewModel.stateMovie.collectAsState()
    val stateActor by movieViewModel.stateActors.collectAsState()
    val stateImage by movieViewModel.stateGallery.collectAsState()
    val stateSimilarMovie by movieViewModel.stateSimilarFilm.collectAsState()
    val isWatched by movieViewModel.isWatched.collectAsState()

    if (stateMovie.isLoading) {

        CircularProgressIndicator(
            modifier = Modifier
        )

    } else if (stateMovie.error.isNotBlank()) {

        Text(
            text = stateMovie.error ,
            modifier = Modifier

        )

    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 100.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ) {
                        val mov = stateMovie.movie
                        if (mov != null) {
                            DetailMovieItem(movie = mov , isWatched , movieViewModel)
                        }
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

                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 26.dp)
                    ) {
                        val mov = stateMovie.movie
                        if (mov != null) {
                            MovieDescriptionItem(movie = mov)
                        }

                        if (stateActor.isLoading) {

                            CircularProgressIndicator(
                                modifier = Modifier
                            )

                        } else if (stateActor.error.isNotBlank()) {

                            Text(
                                text = stateActor.error ,
                                modifier = Modifier

                            )

                        } else {
                            val actors = stateActor.actor
                            val directors = mutableListOf<Actors>()
                            val stuffs = mutableListOf<Actors>()

                            for (actor in actors) {
                                if (actor.professionKey == "ACTOR") {
                                    stuffs.add(actor)
                                } else {
                                    directors.add(actor)
                                }
                            }
                            ActorsList(
                                stuffs ,
                                "В фильме снимались" ,
                                4 ,
                                navController
                            )
                            ActorsList(
                                directors ,
                                "Над фильмом работали" ,
                                2 ,
                                navController
                            )

                        }

                        if (stateImage.isLoading) {

                            CircularProgressIndicator(
                                modifier = Modifier
                            )

                        } else if (stateImage.error.isNotBlank()) {

                            Text(
                                text = stateImage.error ,
                                modifier = Modifier

                            )

                        } else {
                            val images = stateImage.gallary
                            val movie = stateMovie.movie
                            if (images != null && movie != null) {
                                GalleryListItem(
                                    images = images.items ,
                                    navController ,
                                    film = movie
                                )
                            }

                        }

                        if (stateSimilarMovie.isLoading) {

                            CircularProgressIndicator(
                                modifier = Modifier
                            )

                        } else if (stateSimilarMovie.error.isNotBlank()) {

                            Text(
                                text = stateSimilarMovie.error ,
                                modifier = Modifier

                            )

                        } else {
                            val similarMovies = stateSimilarMovie.movies
                            if (similarMovies != null) {
                                SimilarFilmsListItem(
                                    similar = similarMovies.items ,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}