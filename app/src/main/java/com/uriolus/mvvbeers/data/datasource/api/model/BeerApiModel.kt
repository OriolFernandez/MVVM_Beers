package com.uriolus.mvvbeers.data.datasource.api.model

import com.google.gson.annotations.SerializedName

data class BeerApiModel(
    val id: String? = null,
    val name: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val description: String? = null,
)