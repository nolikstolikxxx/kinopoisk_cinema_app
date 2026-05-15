package com.example.kinopoiskCinemaApp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.background(Color.White) ,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 20.dp ,
                        shape = RoundedCornerShape(topStart = 16.dp , topEnd = 16.dp) ,
                    )
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BottomAppBar(
                        modifier = Modifier
                            .wrapContentSize() ,
                        containerColor = Color.White ,

                        ) {
                        BottomNav(navController = navController)
                    }
                }
            }
        } ,
    ) {
        NavGraph(navHostController = navController)
    }
}