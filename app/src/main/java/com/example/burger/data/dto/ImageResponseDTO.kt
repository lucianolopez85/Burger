package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDTO(
    @SerializedName("lg") val lg: String,
    @SerializedName("sm") val sm: String
)