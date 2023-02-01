package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("lexicalEntries") var lexicalEntries: List<LexicalEntryDto>? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("word") var word: String? = null
)