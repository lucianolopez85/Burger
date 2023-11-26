package com.example.burger.data.repository

import com.example.burger.data.api.ServiceAPI
import com.example.burger.data.dto.BurgerResponseDTO
import com.example.burger.data.dto.ImageResponseDTO
import com.example.burger.data.dto.IngredientResponseDTO

class BurgerRepository(private val api: ServiceAPI) {

    suspend fun getBurgers(): List<BurgerResponseDTO> = listMock

//        return api.getBurgers()
//    }
}

val listMock = listOf(
    BurgerResponseDTO(

        desc = "burger clássico, com alface, tomate, cebola roxa e queijo cheddar",
        id = 123,
        imagesResponseDTO = listOf(
            ImageResponseDTO(
                lg = "https://img.freepik.com/fotos-gratis/hamburguer-saboroso-isolado-no-fundo-branco-fastfood-de-hamburguer-fresco-com-carne-e-queijo_90220-1063.jpg?w=140&t=st=1700954167~exp=1700954767~hmac=177f17495e76c2fa92945558a2920ba1c8004f387d1b2bc81697c8ed02bcc28f",
                sm = ""
            )
        ),
        ingredientsResponseDTO = listOf(
            IngredientResponseDTO(
                id = 111,
                img = "https://img.freepik.com/fotos-gratis/queijo-amarelo-e-faca-deitado-no-pano-branco_74855-6118.jpg?w=100&t=st=1700955802~exp=1700956402~hmac=651399286a8b10fa9afa5d485efe26c9d2d15350c61871c48d33878499c80bdd",
                name = "Queijo"
            ),
            IngredientResponseDTO(
                id = 222,
                img = "https://img.freepik.com/fotos-gratis/quadro-completo-de-aneis-de-cebola-vermelha-fatiada_23-2147953547.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=ais",
                name = "Cebola roxa"
            ),
            IngredientResponseDTO(
                id = 333,
                img = "https://img.freepik.com/fotos-gratis/alface-fresca_1339-2067.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=sph",
                name = "Alface"
            ),
            IngredientResponseDTO(
                id = 444,
                img = "https://img.freepik.com/fotos-gratis/fundo-de-bacon_144627-36733.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=sph",
                name = "Bacon"
            )
        ),
        name = "Burger clássico",
        price = 14.6,
        veg = false
    ),
    BurgerResponseDTO(

        desc = "dois hamburguer com alface, tomate, cebola roxa e queijo cheddar",
        id = 123,
        imagesResponseDTO = listOf(
            ImageResponseDTO(
                lg = "https://img.freepik.com/fotos-gratis/hamburguer-duplo-americano-classico-isolado-no-fundo-branco_90220-1194.jpg?w=140&t=st=1700954310~exp=1700954910~hmac=e69b880af6100f9fc8c231d29dcfc7a158098894a314ef71e03b4b3995940e43",
                sm = ""
            )
        ),
        ingredientsResponseDTO = listOf(
            IngredientResponseDTO(
                id = 111,
                img = "https://img.freepik.com/fotos-gratis/queijo-amarelo-e-faca-deitado-no-pano-branco_74855-6118.jpg?w=100&t=st=1700955802~exp=1700956402~hmac=651399286a8b10fa9afa5d485efe26c9d2d15350c61871c48d33878499c80bdd",
                name = "Queijo"
            ),
            IngredientResponseDTO(
                id = 222,
                img = "https://img.freepik.com/fotos-gratis/quadro-completo-de-aneis-de-cebola-vermelha-fatiada_23-2147953547.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=ais",
                name = "Cebola roxa"
            ),
            IngredientResponseDTO(
                id = 333,
                img = "https://img.freepik.com/fotos-gratis/alface-fresca_1339-2067.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=sph",
                name = "Alface"
            ),
            IngredientResponseDTO(
                id = 444,
                img = "https://img.freepik.com/fotos-gratis/fundo-de-bacon_144627-36733.jpg?size=100&ext=jpg&uid=R39411775&ga=GA1.1.1752903141.1700954114&semt=sph",
                name = "Bacon"
            )
        ),
        name = "burger duplo americano",
        price = 26.3,
        veg = false
    )
)