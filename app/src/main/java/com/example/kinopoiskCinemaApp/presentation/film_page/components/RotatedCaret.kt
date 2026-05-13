package com.example.kinopoiskCinemaApp.presentation.film_page.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kinopoiskCinemaApp.R

@Composable
fun RotatedCaret() {
    Image(
        painter = painterResource(R.drawable.caret_left) ,
        contentDescription = "",
        modifier = Modifier
            .size(18.dp),
        colorFilter = ColorFilter.tint(Color(0xFF3D3BFF))
    )
}