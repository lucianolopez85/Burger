package com.example.burger.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burger.data.repository.BurgerRepository
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.domain.vo.BurgerVO
import kotlinx.coroutines.launch

class BurgerViewModel(
    private val repository: BurgerRepository,
    private val converter: BurgerConverter
) : ViewModel() {

    private val _listBurgers = MutableLiveData<List<BurgerVO>>()
    val listBurger: LiveData<List<BurgerVO>> = _listBurgers

    fun fetchData() {

        viewModelScope.launch {
            try {
                val listDDTO = repository.getBurgers()
                val listVO = converter.convert(listDDTO)
                _listBurgers.value = listVO
            } catch (e: Exception) {
                println( "Erro na requisição: ${e.message}")
            }
        }
    }
}
