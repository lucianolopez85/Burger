package com.example.burger.data.api

import com.example.burger.data.dto.BurgerResponseDTO
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.http.GET

interface ServiceAPI {

    @MOCK(asset = "response.json", runDelay = true)
    @GET("burgers")
    suspend fun getBurgers(): List<BurgerResponseDTO>
}
