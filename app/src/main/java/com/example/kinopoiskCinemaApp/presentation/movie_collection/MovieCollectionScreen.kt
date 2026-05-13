package com.example.kinopoiskCinemaApp.presentation.movie_collection

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
import com.example.kinopoiskCinemaApp.domain.model.enums.MoviesCollectionType
import com.example.kinopoiskCinemaApp.domain.model.enums.convertCollectionType
import com.example.kinopoiskCinemaApp.presentation.components.MovieItem

@Composable
fun MovieCollectionScreen(
    navController: NavController ,
    viewModel: MovieCollectionViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        if ( state.isLoading ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

        } else if ( state.error.isNotBlank() ) {

            Text(
                text = state.error,
                modifier = Modifier
                    .align(Alignment.Center)
            )

        } else {

            Column {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .background(Color.Transparent)
                    ) {
                        Icon (
                            painter = painterResource(id = R.drawable.ic_back) ,
                            contentDescription = "Back icon",
                        )
                    }

                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = convertCollectionType(
                            state.collectionType ?:
                            MoviesCollectionType.TOP_250_MOVIES
                        ),
                        style = TextStyle(
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            lineHeight = 13.2.sp,
                            color = Color(0xFF272727)
                        )
                    )
                }

                LazyVerticalGrid (
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(
                            start = 61.dp,
                            end = 61.dp
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.movies) { movie ->
                        MovieItem(
                            movie = movie,
                            onItemClick = {navController.navigate("detailMovie/${movie.kinopoiskId}")}
                        )
                    }
                }

            }
        }
    }
}