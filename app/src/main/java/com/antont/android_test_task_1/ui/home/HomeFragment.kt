package com.antont.android_test_task_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.antont.android_test_task_1.databinding.FragmentHomeBinding
import com.antont.android_test_task_1.ui.view.HighlightedTextView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(
            this, HomeViewModelFactory(requireActivity().application)
        )[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
            showText(it, binding.textView)
        }
        return root
    }

    /**
     * Please use this method to change the displayed text
     */
    private fun showText(text: String, view: HighlightedTextView) {
        view.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}