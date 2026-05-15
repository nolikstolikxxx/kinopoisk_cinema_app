package com.example.kinopoiskCinemaApp.domain.repository

import com.example.kinopoiskCinemaApp.domain.model.Staff

/**
 * Repository interface responsible for
 * staff-related operations.
 *
 * Provides methods for:
 * - Loading staff details;
 * - Loading actors and movie crew information.
 */
interface StaffRepository {

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
    suspend fun getStaffDetails(id: Int): Staff
}