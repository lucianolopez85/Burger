package com.example.burger.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.commons.uiState
import com.example.burger.domain.usecase.BurgerUseCase
import kotlinx.coroutines.launch

class BurgerViewModel(
    private val useCase: BurgerUseCase
) : ViewModel() {

    private val _listBurgers = MutableLiveData<List<BurgerVO>>()
    val listBurger: LiveData<uiState<List<BurgerVO>>> = _listBurgers.map { BurgerList ->
        uiState.Success(BurgerList)
    }

    private val _filteredBurgerList = MutableLiveData<uiState<List<BurgerVO>>>()
    val filteredBurgerList : LiveData<uiState<List<BurgerVO>>> = _filteredBurgerList

    fun fetchData() {
        viewModelScope.launch {
            _listBurgers.value = useCase.getBurgers()
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            _filteredBurgerList.value = uiState.Loading
            try {
                _filteredBurgerList.value = uiState.Success(useCase.filteredBurgerList(name))
                } catch (e: Exception) {
                Log.e("ERROR VIEW_MODEL: ", "${e.message}")
            }
        }
    }
}
