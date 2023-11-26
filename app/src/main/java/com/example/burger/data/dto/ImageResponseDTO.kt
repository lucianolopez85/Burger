package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDTO(
    @SerializedName("lg") var lg: String?,
    @SerializedName("sm") var sm: String?
)