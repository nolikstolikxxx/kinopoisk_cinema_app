package com.example.kinopoiskCinemaApp.presentation.film_page.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinopoiskCinemaApp.domain.model.DetailMovie

@Composable
fun MovieDescriptionItem(movie: DetailMovie){
    Column (
        modifier = Modifier
            .padding(vertical = 40.dp)
    ){
        Text(
            text = movie.shortDescription,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF272727) ,
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 30.dp),
            text = movie.description,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF272727) ,
                hyphens = Hyphens.Auto
            )
        )
    }
}