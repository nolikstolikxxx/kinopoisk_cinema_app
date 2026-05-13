package com.example.kinopoiskCinemaApp.presentation.film_page.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeaderItem(topic: String, list: List<Any>,onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            style = TextStyle(
                fontSize = 20.sp,

                fontWeight = FontWeight(600) ,
                color = Color(0xFF272727) ,
            )
        )
        Row(
            modifier = Modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = list.size.toString(), style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight(600) ,
                color = Color(0xFF3D3BFF) ,
            )
            )
            RotatedCaret()
        }
    }
}