package com.example.kinopoiskCinemaApp.presentation.staff_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.kinopoiskCinemaApp.R
import com.example.kinopoiskCinemaApp.presentation.components.GenreAndAllModel
import com.example.kinopoiskCinemaApp.presentation.components.MovieItem

@Composable
fun StaffDetailScreen(
    staffDetailViewModel: StaffDetailViewModel = hiltViewModel() ,
    navController: NavController
) {

    val state by staffDetailViewModel.state.collectAsState()

    Box(
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .background(Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back) ,
                            contentDescription = "Back icon",
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(horizontal = 26.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .width(146.dp)
                            .height(201.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        model = state.staff?.posterUrl,
                        placeholder = painterResource(id = R.drawable.img) ,
                        error = painterResource(id = R.drawable.img) ,
                        contentDescription = "Staff poster",
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(
                            text = state.staff?.nameRu!!,
                            style = TextStyle(
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                lineHeight = 17.6.sp,
                                color = Color(0xff272727)
                            )
                        )
                        Text(
                            text = state.staff?.profession!!,
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = Color(0xff838390)
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 35.dp)
                ) {
                    GenreAndAllModel(
                        text = "Лучшее",
                        navPath = "staffDetailsMovies/${state.staff?.personId}",
                        navController = navController
                    )
                    LazyRow (
                        modifier = Modifier.padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        items(state.staffMovies.take(7)) { mov ->
                            MovieItem(
                                movie = mov,
                                onItemClick = { navController.navigate("detailMovie/${mov.kinopoiskId}") }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                            .padding(end = 24.dp),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Column {
                            Text(
                                text = "Фильмография",
                                style = TextStyle(
                                    fontWeight = FontWeight.W600,
                                    fontSize = 18.sp,
                                    lineHeight = 19.8.sp

                                ) ,
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = 8.dp),
                                text = state.staff?.films?.size.toString(),
                                style = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 12.sp,
                                    lineHeight = 12.2.sp,
                                    color = Color(0xff838391)
                                )
                            )
                        }
                        TextButton(
                            onClick = {
                                navController.navigate("staffFilmography/${state.staff?.personId!!}")
                            }
                        ) {
                            Text(
                                text = "К списку",
                                style = TextStyle(fontSize = 15.sp) ,
                                color = Color(0xFF3D3BFF) ,
                            )
                        }
                    }
                }
            }
        }
    }
}