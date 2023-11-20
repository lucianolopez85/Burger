package com.example.burger.data.repository

import com.example.burger.data.api.ServiceAPI
import com.example.burger.data.dto.BurgerResponseDTO
import com.example.burger.data.dto.ImageResponseDTO
import com.example.burger.data.dto.IngredientResponseDTO

class BurgerRepository(private val api: ServiceAPI) {

    suspend fun getBurgers(): List<BurgerResponseDTO> {

        return api.getBurgers()
    }
}