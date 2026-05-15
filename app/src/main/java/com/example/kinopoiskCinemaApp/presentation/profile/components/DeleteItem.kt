package com.example.kinopoiskCinemaApp.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinopoiskCinemaApp.R

@Composable
fun DeleteItem(
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(111.dp)
            .height(194.dp)
            .background(
                color = Color.White ,
                shape = RoundedCornerShape(22.dp)
            )
            .clickable { onItemClick() } ,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally ,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.White ,
                        shape = CircleShape
                    ) ,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_trash) ,
                    contentDescription = null ,
                    tint = Color.Unspecified
                )
            }

            Text(
                text = "Очистить\nисторию" ,
                textAlign = TextAlign.Center ,
                color = Color(0xFF272727) ,
                style = TextStyle(
                    fontWeight = FontWeight.W400 ,
                    fontSize = 12.sp ,
                    lineHeight = 13.2.sp
                )
            )
        }
    }
}