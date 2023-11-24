package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class BurgerResponseDTO(
    @SerializedName("desc") val desc: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("images") val imagesResponseDTO: List<ImageResponseDTO?>?,
    @SerializedName("ingredients") val ingredientsResponseDTO: List<IngredientResponseDTO?>?,
    @SerializedName("name") val name: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("veg") val veg: Boolean?

)
