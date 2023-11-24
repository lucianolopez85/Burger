package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.burger.R
import com.example.burger.databinding.FragmentBurgerBinding
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.ui.adapter.BurgerAdapter
import com.example.burger.ui.viewmodel.BurgerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BurgerFragment : Fragment(R.layout.fragment_burger) {
    private val binding by lazy { FragmentBurgerBinding.bind(requireView()) }
    private val viewModel : BurgerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBurgers()
    }

    private fun getBurgers() {
        viewModel.listBurger.observe(viewLifecycleOwner) {
            showSuccess(it)
        }
        viewModel.fetchData()
    }

    private fun showSuccess(data: List<BurgerVO>) {
        binding.refreshLayout.isRefreshing = false

        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = BurgerAdapter(data) {
                findNavController().navigate(R.id.action_burgerFragment_to_detailsFragment)

            }
        }
    }

}