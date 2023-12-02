package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.databinding.FragmentBurgerBinding
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.ui.adapter.BurgerAdapter
import com.example.burger.ui.viewmodel.BurgerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BurgerFragment : Fragment(R.layout.fragment_burger) {
    private val binding by lazy { FragmentBurgerBinding.bind(requireView()) }
    private val viewModel: BurgerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBurgers()
        setupSearch()
    }

    private fun setupSearch() {
        binding.searchBurger.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    getBurgerByName(query)
                    true
                } else false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val closeButton: View? =
            binding.searchBurger.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.searchBurger.setQuery("", false)
            binding.searchBurger.clearFocus()
            getBurgers()
        }
    }

    private fun getBurgers() {
        viewModel.listBurger.observe(viewLifecycleOwner) {
            showSuccess(it)
        }
        viewModel.fetchData()
    }

    private fun getBurgerByName(name: String) {
        viewModel.searchBurgers.observe(viewLifecycleOwner) {
            val burgerAdapter = BurgerAdapter { burger ->
                val bundle = bundleOf("burgerVO" to burger)
                findNavController().navigate(R.id.action_burgerFragment_to_detailsFragment, bundle)
            }
            with(binding.recyclerView) {
                setHasFixedSize(true)
                adapter = burgerAdapter
                burgerAdapter.submitList(it)
            }
        }
        viewModel.searchByName(name)
    }

    private fun showSuccess(data: List<BurgerVO>) {
        binding.refreshLayout.isRefreshing = false

        val burgerAdapter = BurgerAdapter { burger ->
            val bundle = bundleOf("burgerVO" to burger)
            findNavController().navigate(R.id.action_burgerFragment_to_detailsFragment, bundle)
        }

        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = burgerAdapter
            burgerAdapter.submitList(data)
        }
    }
}