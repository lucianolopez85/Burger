package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.commons.uiState
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
        observeBurgerListState()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchBurger.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null) {
                    observeSearchedBurgers(newText)
                    true
                } else false
            }
        })
        val closeButton: View? =
            binding.searchBurger.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            binding.searchBurger.setQuery("", false)
            binding.searchBurger.clearFocus()
            observeBurgerListState()
        }
    }

    private fun observeSearchedBurgers(name: String) {
        viewModel.searchByName(name)
        viewModel.filteredBurgerList.observe(viewLifecycleOwner) { state ->
            when(state) {
                is uiState.Loading -> showLoading()
                is uiState.Success -> showSuccess(state.data)
                is uiState.Error -> showError()
            }
        }
    }

    private fun observeBurgerListState() {
        viewModel.listBurger.observe(viewLifecycleOwner) { state ->
            when (state) {
                is uiState.Loading -> showLoading()
                is uiState.Success -> showSuccess(state.data)
                is uiState.Error -> showError()
            }
        }
        viewModel.fetchData()
    }

    private fun showLoading() {
        binding.refreshLayout.isVisible = true
        binding.refreshLayout.isRefreshing = true
    }

    private fun showSuccess(data: List<BurgerVO>) {
        binding.refreshLayout.isRefreshing = false
        val burgerAdapter = BurgerAdapter(data) { burger ->
            val bundle = bundleOf("burgerVO" to burger)
            findNavController().navigate(R.id.action_burgerFragment_to_detailsFragment, bundle)

        }
        with(binding.recyclerView) {
            setHasFixedSize(true)
            adapter = burgerAdapter
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(),"ERROR", Toast.LENGTH_LONG).show()
    }
}