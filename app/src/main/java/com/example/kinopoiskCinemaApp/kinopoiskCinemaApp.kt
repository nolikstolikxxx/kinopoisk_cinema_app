package com.example.kinopoiskCinemaApp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class of the project.
 *
 * Initializes:
 * - Hilt dependency injection;
 * - Global application configuration.
 */
@HiltAndroidApp
class KinopoiskCinemaApp : Application() {
}