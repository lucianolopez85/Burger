package com.example.burger.data.dto

data class BurgerResponseDTO(
    val desc: String?,
    val id: Int?,
    val imageResponseDTO: List<ImageResponseDTO?>?,
    val ingredientResponsDTO: List<IngredientResponseDTO?>?,
    val name: String?,
    val price: Double?,
    val veg: Boolean?

)
