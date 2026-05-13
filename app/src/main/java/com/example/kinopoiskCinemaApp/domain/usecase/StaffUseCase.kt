package com.example.kinopoiskCinemaApp.domain.usecase

import com.example.kinopoiskCinemaApp.domain.model.Staff
import com.example.kinopoiskCinemaApp.domain.repository.StaffRepository
import javax.inject.Inject

class StaffUseCase @Inject constructor(
    private val staffRepository: StaffRepository
) {
    suspend fun getStuffDetailsById(id: Int): Staff {
        return staffRepository.getStaffDetails(id)
    }
}