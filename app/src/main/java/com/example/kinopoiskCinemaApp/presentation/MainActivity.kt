package com.example.kinopoiskCinemaApp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kinopoiskCinemaApp.presentation.navigation.MainScreen
import com.example.kinopoiskCinemaApp.presentation.onboarding.OnboardingScreen
import com.example.kinopoiskCinemaApp.presentation.ui.theme.KinopoiskSkillCinemaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KinopoiskSkillCinemaTheme {
                OnboardingScreen {
                    startMainScreen()
                }
            }
        }
    }

    private fun startMainScreen() {
        setContent {
            KinopoiskSkillCinemaTheme {
                MainScreen()
            }
        }
    }
}