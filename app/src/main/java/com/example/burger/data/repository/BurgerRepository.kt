package com.example.burger.data.repository

import com.example.burger.data.dto.BurgerResponseDTO

interface BurgerRepository {

    suspend fun getBurgers(): List<BurgerResponseDTO>

}
