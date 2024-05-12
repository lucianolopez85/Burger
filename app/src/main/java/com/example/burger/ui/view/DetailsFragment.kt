package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.commons.formattedCurrency
import com.example.burger.databinding.FragmentDetailsBinding
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.domain.vo.IngredientVO
import com.example.burger.ui.adapter.IngredientsAdapter
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
        burgerId.let { burgerVO ->
            binding.titleBurgerDetaiL.text = burgerVO.name
            binding.descriptionBurgerDetail.text = burgerVO.desc
            binding.priceDetail.text = burgerVO.price?.formattedCurrency()

            Picasso
                .get()
                .load(burgerVO.imageVO?.get(1)?.lg)
                .into(binding.imageBurgerDetail)

            initRecyclerView(burgerVO.ingredientVO ?: emptyList())
        }
    }

    private fun initRecyclerView(ingredients: List<IngredientVO?>?) {
        val ingredientsAdapter = IngredientsAdapter()
        with(binding.rvIngredients) {
            adapter = ingredientsAdapter
            ingredientsAdapter.submitList(ingredients)
        }
    }
}