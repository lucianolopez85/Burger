package com.example.burger.data.api

import com.example.burger.data.dto.BurgerResponseDTO
import retrofit2.http.GET

interface ServiceAPI {

    @GET("burgers")
    suspend fun getBurgers(): List<BurgerResponseDTO>

}