package com.example.kinopoiskCinemaApp.data.repository

import com.example.kinopoiskCinemaApp.data.network.KinopoiskApi
import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.repository.StaffRepository
import javax.inject.Inject

/**
 * Repository implementation responsible for
 * loading staff-related information.
 *
 * Works with:
 * - Staff details;
 * - Actors and movie crew information.
 */
class StaffRepositoryImpl @Inject constructor(

    /**
     * Retrofit API service.
     */
    private val api: KinopoiskApi

) : StaffRepository {

    // ================================
    // Staff Details
    // ================================

    /**
     * Loads detailed information about
     * selected staff member.
     *
     * @param id Staff member identifier.
     *
     * @return Staff object.
     */
    override suspend fun getStaffDetails(id: Int): Staff {
        return api.getStaffDetails(id)
    }
}