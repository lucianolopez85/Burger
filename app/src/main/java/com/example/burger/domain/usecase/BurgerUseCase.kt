package com.example.burger.domain.usecase

import com.example.burger.data.repository.BurgerRepository
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.domain.vo.BurgerVO

class BurgerUseCase(
    private val repository: BurgerRepository,
    private val converter: BurgerConverter
) {

    suspend fun getBurgers(): List<BurgerVO> {
        val burgerResponseDTO = repository.getBurgers()
        return  converter.convert(burgerResponseDTO)
    }
    suspend fun filteredBurgerList(name: String): List<BurgerVO> {
        val burgerResponseDTO = repository.getBurgers()
        val filteredList = converter.convert(burgerResponseDTO)
        return filteredList.filter { it.name?.contains(name, true) == true }
    }

}
