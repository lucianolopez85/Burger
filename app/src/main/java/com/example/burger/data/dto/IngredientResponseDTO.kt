package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class IngredientResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String
)