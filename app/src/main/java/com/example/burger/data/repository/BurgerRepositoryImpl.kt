package com.example.burger.data.repository

import com.example.burger.data.api.ServiceAPI
import com.example.burger.data.dto.BurgerResponseDTO

class BurgerRepositoryImpl(
    private val api: ServiceAPI
): BurgerRepository {
    override suspend fun getBurgers(): List<BurgerResponseDTO> =
        api.getBurgers()

}
