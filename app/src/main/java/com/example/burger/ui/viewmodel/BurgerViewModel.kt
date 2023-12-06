package com.example.burger.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burger.data.repository.BurgerRepository
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.commons.uiState
import kotlinx.coroutines.launch

class BurgerViewModel(
    private val repository: BurgerRepository,
    private val converter: BurgerConverter
) : ViewModel() {

    private val _listBurgers = MutableLiveData<uiState<List<BurgerVO>>>()
    val listBurger: LiveData<uiState<List<BurgerVO>>> = _listBurgers

    private val _searchBurgers = MutableLiveData<List<BurgerVO>>()
    val searchBurgers: LiveData<List<BurgerVO>> = _searchBurgers

    fun fetchData() {
        viewModelScope.launch {
            _listBurgers.value = uiState.Loading
            try {
                val listDTO = repository.getBurgers()
                val listVO = converter.convert(listDTO)
                _listBurgers.value = uiState.Success(listVO)
            } catch (e: Exception) {
                _listBurgers.value = uiState.Error(e)
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            try {
                val listDTO = repository.getBurgerByName(name)
                val listVO = converter.convert(listDTO)
                val filteredNomes = listVO.filter { it.name?.contains(name, true) == true }
                _searchBurgers.value = filteredNomes
            } catch (e: Exception) {
                println("Erro na requisição: ${e.message}")
            }
        }
    }
}
