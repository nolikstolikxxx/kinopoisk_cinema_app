package com.example.kinopoiskCinemaApp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kinopoiskCinemaApp.databinding.FragmentHomeBinding

/**
 * Fragment representing the Home screen.
 *
 * Responsible for:
 * - Initializing ViewBinding;
 * - Observing ViewModel data;
 * - Updating UI content;
 * - Properly clearing binding references.
 */
class HomeFragment : Fragment() {

    // ================================
    // ViewBinding
    // ================================

    /**
     * Backing property for ViewBinding.
     */
    private var _binding: FragmentHomeBinding? = null

    /**
     * Non-nullable binding reference.
     *
     * Valid only between:
     * - onCreateView()
     * - onDestroyView()
     */
    private val binding get() = _binding!!

    // ================================
    // Lifecycle
    // ================================

    /**
     * Creates and initializes fragment UI.
     */
    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {

        // Initialize ViewModel
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        // Inflate ViewBinding
        _binding = FragmentHomeBinding.inflate(
            inflater ,
            container ,
            false
        )

        val root: View = binding.root

        // ================================
        // UI Components
        // ================================

        /**
         * TextView displaying data from ViewModel.
         */
        val textView: TextView = binding.textHome

        // ================================
        // Observers
        // ================================

        /**
         * Observe text changes from ViewModel
         * and update UI.
         */
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    /**
     * Clears ViewBinding reference
     * to prevent memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}