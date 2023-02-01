package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class SenseDto(
    @SerializedName("definitions") var definitions: List<String>? = null,
    @SerializedName("id") var id: String? = null
)