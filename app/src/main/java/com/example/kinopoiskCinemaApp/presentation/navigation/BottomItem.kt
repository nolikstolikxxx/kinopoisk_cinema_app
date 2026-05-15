package com.example.kinopoiskCinemaApp.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.kinopoiskCinemaApp.R

/**
 * Sealed class representing items
 * of the bottom navigation bar.
 *
 * Contains:
 * - Navigation route;
 * - Icon resource.
 */
sealed class BottomNavItem(

    /**
     * Drawable resource for navigation icon.
     */
    @param:DrawableRes val iconId: Int ,

    /**
     * Navigation destination route.
     */
    val route: String
) {
    // ================================
    // Navigation Items
    // ================================

    /**
     * Home screen navigation item.
     */
    data object Homepage : BottomNavItem(R.drawable.ic_home , NavigationRoutes.HOME)

    /**
     * Search screen navigation item.
     */
    data object Search : BottomNavItem(R.drawable.ic_search , NavigationRoutes.SEARCH)

    /**
     * Profile screen navigation item.
     */
    data object Profile : BottomNavItem(R.drawable.ic_profile , NavigationRoutes.PROFILE)
}