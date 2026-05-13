package com.example.kinopoiskCinemaApp.data.repository

import com.example.kinopoiskCinemaApp.data.network.KinopoiskApi
import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.repository.StaffRepository
import javax.inject.Inject

class StaffRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi
): StaffRepository {

    override suspend fun getStaffDetails(id: Int): Staff {
        return api.getStaffDetails(id)
    }
}