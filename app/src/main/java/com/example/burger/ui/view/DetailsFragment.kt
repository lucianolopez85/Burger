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
import com.example.burger.navigation.BurgerNavigation
import com.example.burger.ui.adapter.IngredientsAdapter
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        private const val DETAIL_DATA = "BURGER_DETAIL_DATA"
    }

    private val binding by lazy { FragmentDetailsBinding.bind(requireView()) }
    private val burgerVO by lazy { arguments?.getSerializable(DETAIL_DATA) as BurgerVO }
    private val navigation: BurgerNavigation by inject { parametersOf(findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupLayout()
    }

    private fun setupToolbar() {
        binding.iconBackDetail.setOnClickListener {
            navigation.gotoBurgerList()
        }
    }

    private fun setupLayout() {
        burgerVO.let { burgerVO ->
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
