package com.example.kinopoiskCinemaApp.domain.repository

import com.example.kinopoiskCinemaApp.domain.model.Staff

interface StaffRepository {
    suspend fun getStaffDetails(id: Int): Staff
}