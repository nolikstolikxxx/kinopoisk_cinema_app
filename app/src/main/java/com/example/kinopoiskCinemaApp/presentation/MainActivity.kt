package com.example.kinopoiskCinemaApp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kinopoiskCinemaApp.presentation.navigation.MainScreen
import com.example.kinopoiskCinemaApp.presentation.onboarding.OnboardingScreen
import com.example.kinopoiskCinemaApp.presentation.ui.theme.KinopoiskSkillCinemaTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point of the application.
 *
 * Responsible for:
 * - Initial application setup;
 * - Launching onboarding flow;
 * - Launching main application screen.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ================================
    // Activity Lifecycle
    // ================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KinopoiskSkillCinemaTheme {

                /**
                 * Launch onboarding screen first.
                 */
                OnboardingScreen {
                    startMainScreen()
                }
            }
        }
    }

    // ================================
    // Navigation
    // ================================

    /**
     * Opens main application screen
     * after onboarding is completed.
     */
    private fun startMainScreen() {
        setContent {
            KinopoiskSkillCinemaTheme {
                MainScreen()
            }
        }
    }
}