package com.example.kinopoiskCinemaApp.presentation.gallery.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kinopoiskCinemaApp.R
import com.example.kinopoiskCinemaApp.domain.model.Image

@Composable
fun GalleryItem(gallery: Image) {
    Card(
        shape = RoundedCornerShape(8.dp) ,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .fillMaxWidth(),
            model = gallery.imageUrl,
            placeholder = painterResource(id = R.drawable.img) ,
            error = painterResource(id = R.drawable.img) ,
            contentDescription = "Movie poster",
            contentScale = ContentScale.Crop
        )
    }
}