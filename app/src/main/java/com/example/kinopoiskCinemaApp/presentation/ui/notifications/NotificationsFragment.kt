package com.example.kinopoiskCinemaApp.presentation.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kinopoiskCinemaApp.databinding.FragmentNotificationsBinding

/**
 * Fragment representing the Notifications screen.
 *
 * Responsible for:
 * - Initializing ViewBinding;
 * - Observing ViewModel data;
 * - Updating UI content;
 * - Properly clearing binding references.
 */
class NotificationsFragment : Fragment() {

    // ================================
    // ViewBinding
    // ================================

    /**
     * Backing property for ViewBinding.
     */
    private var _binding: FragmentNotificationsBinding? = null

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
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        // Inflate ViewBinding
        _binding = FragmentNotificationsBinding.inflate(
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
        val textView: TextView = binding.textNotifications

        // ================================
        // Observers
        // ================================

        /**
         * Observe text changes from ViewModel
         * and update UI.
         */
        notificationsViewModel.text.observe(viewLifecycleOwner) {
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