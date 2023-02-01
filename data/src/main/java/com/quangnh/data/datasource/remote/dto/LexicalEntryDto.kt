package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class LexicalEntryDto(
    @SerializedName("entries") var entries: List<EntryDto>? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("lexicalCategory") var lexicalCategory: LexicalCategoryDto? = null,
    @SerializedName("text") var text: String? = null
)