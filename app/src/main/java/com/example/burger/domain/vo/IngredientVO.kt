package com.example.burger.domain.vo

import java.io.Serializable

data class IngredientVO(
    val id: Int?,
    val img: String?,
    val name: String?
) : Serializable