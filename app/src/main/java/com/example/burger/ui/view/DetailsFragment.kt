package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.commons.formattedCurrency
import com.example.burger.databinding.FragmentDetailsBinding
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.ui.viewmodel.BurgerViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by lazy { FragmentDetailsBinding.bind(requireView()) }
    private val viewModel: BurgerViewModel by viewModel()
    private val burgerId by lazy { arguments?.getSerializable("burgerVO") as BurgerVO }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupLayout()
    }

    private fun setupToolbar() {
        binding.iconBackDetail.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_burgerFragment)
        }
    }

    private fun setupLayout() {
        burgerId.let { burger ->
            binding.titleBurgerDetaiL.text = burger.name
            binding.descriptionBurgerDetail.text = burger.desc
            binding.priceDetail.text = burger.price?.formattedCurrency()

            Picasso
                .get()
                .load(burger.imageVO?.get(0)?.lg)
                .into(binding.imageBurgerDetail)
        }
    }
}