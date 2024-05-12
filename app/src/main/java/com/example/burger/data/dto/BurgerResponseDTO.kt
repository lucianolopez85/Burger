package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class BurgerResponseDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("images") val imagesResponseDTO: List<ImageResponseDTO?>?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("ingredients") val ingredientsResponseDTO: List<IngredientResponseDTO?>?,
    @SerializedName("price") val price: Double?,
    @SerializedName("veg") val veg: Boolean?

)
