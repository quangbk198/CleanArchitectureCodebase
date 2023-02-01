package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class LexicalCategoryDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("text") var text: String? = null
)