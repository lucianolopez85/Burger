package com.example.burger.domain.usecase

import com.example.burger.data.repository.BurgerRepository
import com.example.burger.domain.converter.BurgerConverter
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.domain.vo.SearchFilterVO

class BurgerUseCase(
    private val repository: BurgerRepository,
    private val converter: BurgerConverter
) {

    suspend fun getBurgers(): List<BurgerVO> {
        val burgerResponseDTO = repository.getBurgers()
        return converter.convert(burgerResponseDTO)
    }

    fun newSearchList(sourceData: List<BurgerVO>): List<SearchFilterVO> =
        converter.convertSearch(sourceData)

}
