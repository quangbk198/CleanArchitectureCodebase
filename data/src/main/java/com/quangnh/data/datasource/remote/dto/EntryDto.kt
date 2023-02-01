package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class EntryDto(
    @SerializedName("senses") var senses: List<SenseDto>? = null
)