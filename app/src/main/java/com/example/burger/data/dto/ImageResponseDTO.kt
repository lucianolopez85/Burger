package com.example.burger.data.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDTO(
    @SerializedName("sm") var sm: String?,
    @SerializedName("lg") var lg: String?
)