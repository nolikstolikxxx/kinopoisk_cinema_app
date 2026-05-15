package com.example.kinopoiskCinemaApp.presentation.onboarding


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinopoiskCinemaApp.R

@Composable
fun Onboarding(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(151.dp , Alignment.Start) ,
            verticalAlignment = Alignment.CenterVertically ,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 26.dp ,
                    y = 80.dp
                )
                .requiredWidth(width = 308.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.vector) ,
                contentDescription = "Vector" ,
                modifier = Modifier
                    .requiredWidth(width = 120.dp)
                    .requiredHeight(height = 18.dp)
            )
            StatusGhostStateDisabledSizeSmallIconNoLabelIconNo()
        }
        Image(
            painter = painterResource(id = R.drawable.onboarding1) ,
            contentDescription = "wfh_4 1" ,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 0.dp ,
                    y = 265.dp
                )
                .requiredWidth(width = 360.dp)
                .requiredHeight(height = 270.dp)
        )
        Text(
            text = "Узнавай о премьерах" ,
            color = Color(0xff272727) ,
            style = TextStyle(
                fontSize = 32.sp ,
                fontWeight = FontWeight.Medium
            ) ,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 26.dp ,
                    y = 602.24.dp
                )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp , Alignment.Start) ,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 26.dp ,
                    y = 728.24.dp
                )
        ) {
            Box(
                modifier = Modifier
                    .requiredSize(size = 8.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xff121616))
            )
            Box(
                modifier = Modifier
                    .requiredSize(size = 8.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffd9d9d9))
            )
            Box(
                modifier = Modifier
                    .requiredSize(size = 8.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffd9d9d9))
            )
        }
    }
}

@Composable
fun StatusGhostStateDisabledSizeSmallIconNoLabelIconNo(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp , Alignment.End) ,
        verticalAlignment = Alignment.CenterVertically ,
        modifier = modifier
            .clip(shape = RoundedCornerShape(56.dp))
    ) {
        Text(
            text = "Пропустить" ,
            color = Color(0xffb5b5c9) ,
            textAlign = TextAlign.Center ,
            style = TextStyle(
                fontSize = 14.sp ,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(widthDp = 360 , heightDp = 800)
@Composable
private fun OnboardingPreview() {
    Onboarding(Modifier)
}