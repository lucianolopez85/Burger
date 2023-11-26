package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class IngredientResponseDTO(
    @SerializedName("id") var id: Int?,
    @SerializedName("img") var img: String?,
    @SerializedName("name") var name: String?
)