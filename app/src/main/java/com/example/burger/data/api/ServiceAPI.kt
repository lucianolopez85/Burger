package com.example.burger.data.api

import com.example.burger.data.dto.BurgerResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("burgers")
    suspend fun getBurgers(): List<BurgerResponseDTO>

    @GET("find-buger/")
    suspend fun getBurgerByName(
        @Query("search") name: String
    ): List<BurgerResponseDTO>

}