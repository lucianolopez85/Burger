package com.example.burger.domain.vo

import java.io.Serializable

data class SearchFilterVO(
    override val desc: String?,
    override val id: Int?,
    override val imageVO: List<ImageVO?>?,
    override val ingredientVO: List<IngredientVO?>?,
    override val name: String?,
    override val price: Double?,
    override val veg: Boolean?

) : BurgerItem, Serializable
