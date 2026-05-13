package com.example.kinopoiskCinemaApp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel responsible for managing Home screen data.
 *
 * Provides observable text data for the UI layer.
 */
class HomeViewModel : ViewModel() {

    // ================================
    // State
    // ================================

    /**
     * Internal mutable text state.
     */
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    /**
     * Public immutable LiveData exposed to the UI.
     */
    val text: LiveData<String> = _text
}