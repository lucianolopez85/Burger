package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {


    private val binding by lazy {FragmentDetailsBinding.bind(requireView())}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.iconBackDetail.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_burgerFragment)
        }
    }
}