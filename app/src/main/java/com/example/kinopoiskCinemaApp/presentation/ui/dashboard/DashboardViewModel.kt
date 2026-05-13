package com.example.kinopoiskCinemaApp.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel responsible for managing Dashboard screen data.
 *
 * Provides observable text data for the UI layer.
 */
class DashboardViewModel : ViewModel() {

    // ================================
    // State
    // ================================

    /**
     * Internal mutable text state.
     */
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    /**
     * Public immutable LiveData exposed to the UI.
     */
    val text: LiveData<String> = _text
}