package com.example.kinopoiskCinemaApp.presentation.home_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kinopoiskCinemaApp.domain.model.enums.convertCollectionType
import com.example.kinopoiskCinemaApp.presentation.components.GenreAndAllModel
import com.example.kinopoiskCinemaApp.presentation.components.MovieItem

@Composable
fun HomePageScreen(
    viewModel: HomePageViewModel = hiltViewModel() ,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        if (state.isLoading) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

        } else if (state.error.isNotBlank()) {

            Text(
                text = state.error ,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        } else {

            Column(
                modifier = Modifier.padding(top = 30.dp , start = 24.dp)
            ) {
                Text(
                    text = "Skillcinema" ,
                    style = TextStyle(fontSize = 22.sp)
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 16.dp , bottom = 80.dp)

                ) {
                    items(state.movies.toList()) { (key , value) ->

                        GenreAndAllModel(
                            text = convertCollectionType(key) ,
                            navPath = "movieCollection/${key}" ,
                            navController = navController
                        )
                        LazyRow(
                            modifier = Modifier.padding(bottom = 16.dp) ,
                            horizontalArrangement = Arrangement.spacedBy(9.dp)
                        ) {
                            items(value.take(7)) { mov ->
                                MovieItem(
                                    movie = mov ,
                                    onItemClick = {
                                        navController
                                            .navigate("detailMovie/${mov.kinopoiskId}")
                                    }
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}