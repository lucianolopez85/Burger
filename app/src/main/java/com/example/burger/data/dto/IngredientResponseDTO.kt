package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class IngredientResponseDTO(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("img") var img: String?
)