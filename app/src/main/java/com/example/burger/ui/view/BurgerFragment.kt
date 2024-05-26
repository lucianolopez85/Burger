package com.example.burger.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.burger.R
import com.example.burger.commons.uiState
import com.example.burger.databinding.FragmentBurgerBinding
import com.example.burger.domain.vo.BurgerItem
import com.example.burger.navigation.BurgerNavigation
import com.example.burger.ui.adapter.BurgerAdapter
import com.example.burger.ui.viewmodel.BurgerViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BurgerFragment : Fragment(R.layout.fragment_burger) {

    private val binding by lazy { FragmentBurgerBinding.bind(requireView()) }
    private val viewModel: BurgerViewModel by viewModel()
    private val navigation: BurgerNavigation by inject { parametersOf(findNavController()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBurgerListState()
        setupToolbar()
        setupSearchView()
    }

    private fun setupToolbar() = with(binding.componentToolbar) {
        isVisible = true
        initToolbar(
            visibleIconLeft = true,
            visibleIconRight = false,
            title = resources.getString(R.string.list_burgers_title)
        )
    }

    private fun setupSearchView() = with(binding.searchBurger) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        val closeButton: View? = findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton?.setOnClickListener {
            setQuery("", false)
            clearFocus()
            observeBurgerListState()
        }
    }

    private fun observeSearchedBurgers(name: String) = with(viewModel) {
        searchByName(name)
        filteredBurgerList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is uiState.Loading -> showLoading()
                is uiState.Success -> {
                    showSuccess(state.data)

                }

                is uiState.Error -> showError(state.data)
            }
        }
    }

    private fun observeBurgerListState() = with(viewModel) {
        listBurger.observe(viewLifecycleOwner) { state ->
            when (state) {
                is uiState.Loading -> showLoading()
                is uiState.Success -> showSuccess(state.data)
                is uiState.Error -> showError(state.data)
            }
        }
        fetchData()
    }

    private fun showLoading() = with(binding.refreshLayout) {
        isVisible = true
        isRefreshing = true
    }

    private fun showSuccess(data: List<BurgerItem>) = with(binding) {
        refreshLayout.isRefreshing = false
        val burgerAdapter = BurgerAdapter(data) { burger ->
            navigation.gotoDetails(burger)
        }
        burgerAdapter.updateData(data)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = burgerAdapter
    }

    private fun showError(data: Throwable?) = with(binding) {
        alertView.setupError(data) {
            viewModel.fetchData()
        }
        refreshLayout.isVisible = false
        searchBurger.isVisible = false
    }
}
