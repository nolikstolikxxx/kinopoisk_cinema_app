package com.example.kinopoiskCinemaApp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kinopoiskCinemaApp.R
import com.example.kinopoiskCinemaApp.presentation.ui.theme.KinopoiskSkillCinemaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(onComplete: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0 , pageCount = { 3 })
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 26.dp) ,
        topBar = {
            TopAppBar(
                title = { Text(text = "Skillcinema") } ,
                actions = {
                    TextButton(onClick = {
                        onComplete()
                    }) {
                        Text(text = "Пропустить" , color = Color.Gray)
                    }
                }
            )
        } ,
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                HorizontalPager(
                    state = pagerState ,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    when (page) {
                        0 -> OnboardingPage(
                            imageRes = R.drawable.onboarding1 ,
                            title = "Узнавай о премьерах"
                        )

                        1 -> OnboardingPage(
                            imageRes = R.drawable.onboarding2 ,
                            title = "Создавай коллекции"
                        )

                        2 -> OnboardingPage(
                            imageRes = R.drawable.onboarding3 ,
                            title = "Делись с друзьями"
                        )
                    }
                }
                PageIndicator(
                    currentPage = pagerState.currentPage ,
                    pageCount = 3 ,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 63.dp , start = 26.dp)
                )
            }
        }
    )
}

@Composable
fun OnboardingPage(imageRes: Int , title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize() ,
        horizontalAlignment = Alignment.CenterHorizontally ,
    ) {
        Image(
            painter = painterResource(id = imageRes) ,
            contentDescription = null ,
            modifier = Modifier
                .weight(1f)
        )
        Text(
            text = title ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 56.dp , start = 26.dp) ,
            fontSize = 32.sp ,
            textAlign = TextAlign.Start


        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    KinopoiskSkillCinemaTheme {
        OnboardingScreen(onComplete = {})
    }
}

@Composable
fun PageIndicator(currentPage: Int , pageCount: Int , modifier: Modifier = Modifier) {
    Row(
        modifier = modifier ,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { index ->
            val color = if (index == currentPage) Color.Blue else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(12.dp)
                    .background(color , shape = CircleShape)
            )
        }
    }
}