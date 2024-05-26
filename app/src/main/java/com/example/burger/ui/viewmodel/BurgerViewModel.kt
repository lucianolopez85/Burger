package com.example.burger.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.commons.uiState
import com.example.burger.domain.usecase.BurgerUseCase
import com.example.burger.domain.vo.SearchFilterVO
import kotlinx.coroutines.launch

class BurgerViewModel(
    private val useCase: BurgerUseCase
) : ViewModel() {

    private val _listBurgers = MutableLiveData<uiState<List<BurgerVO>>>()
    val listBurger: LiveData<uiState<List<BurgerVO>>> = _listBurgers

    private val _filteredBurgerList = MutableLiveData<uiState<List<SearchFilterVO>>>()
    val filteredBurgerList: LiveData<uiState<List<SearchFilterVO>>> = _filteredBurgerList

    private var originalBurgerList: List<BurgerVO> = emptyList()

    fun fetchData() {
        viewModelScope.launch {
            try {
                val burgers = useCase.getBurgers()
                _listBurgers.value = uiState.Success(burgers)
                originalBurgerList = burgers
            } catch (e: Exception) {
                _listBurgers.value = uiState.Error(e)
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            _filteredBurgerList.value = uiState.Loading
            try {
                val filteredList =
                    originalBurgerList.filter { it.name?.contains(name, true) == true }
                val searchResults = useCase.newSearchList(filteredList)
                _filteredBurgerList.value = uiState.Success(searchResults)
            } catch (e: Exception) {
                Log.e("ERROR VIEW_MODEL: ", "${e.message}")
            }
        }
    }
}
