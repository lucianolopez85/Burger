package com.example.burger.domain.converter

import com.example.burger.data.dto.BurgerResponseDTO
import com.example.burger.domain.vo.BurgerVO
import com.example.burger.domain.vo.ImageVO
import com.example.burger.domain.vo.IngredientVO
import com.example.burger.domain.vo.SearchFilterVO

class BurgerConverter {

    fun convert(data: List<BurgerResponseDTO>) : List<BurgerVO> =
        data.map { dto->
            BurgerVO(
                desc = dto.desc,
                id = dto.id,
                imageVO = dto.imagesResponseDTO?.map {
                       ImageVO(
                           lg = it?.lg,
                           sm = it?.sm
                       )
                },
                ingredientVO = dto.ingredientsResponseDTO?.map {
                    IngredientVO(
                        id = it?.id,
                        img = it?.img,
                        name = it?.name
                    )
                },
                name = dto.name,
                price = dto.price,
                veg = dto.veg
            )
        }

    fun convertSearch(data: List<BurgerVO>) : List<SearchFilterVO> =
        data.map {vo ->
            SearchFilterVO(
                desc = vo.desc,
                id = vo.id,
                imageVO = vo.imageVO?.map {
                    ImageVO(
                        lg = it?.lg,
                        sm = it?.sm
                    )
                },
                ingredientVO = vo.ingredientVO?.map {
                    IngredientVO(
                        id = it?.id,
                        img = it?.img,
                        name = it?.name
                    )
                },
                name = vo.name,
                price = vo.price,
                veg = vo.veg
            )
        }
}