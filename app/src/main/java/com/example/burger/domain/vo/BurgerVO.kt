package com.example.burger.domain.vo

data class BurgerVO(
    val desc: String?,
    val id: Int?,
    val imageVO: List<ImageVO?>?,
    val ingredientVO: List<IngredientVO?>?,
    val name: String?,
    val price: Double?,
    val veg: Boolean?

)
