package com.example.kinopoiskCinemaApp.domain.usecase

import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.repository.StaffRepository
import javax.inject.Inject

/**
 * UseCase responsible for staff-related business logic.
 *
 * Handles:
 * - Loading staff details;
 * - Preparing staff data for presentation layer.
 */
class StaffUseCase @Inject constructor(

    /**
     * Repository for staff-related operations.
     */
    private val staffRepository: StaffRepository

) {

    // ================================
    // Staff Details
    // ================================

    /**
     * Loads detailed information
     * about selected staff member.
     *
     * @param id Staff member identifier.
     *
     * @return Staff object.
     */
    suspend fun getStuffDetailsById(id: Int): Staff {
        return staffRepository.getStaffDetails(id)
    }
}