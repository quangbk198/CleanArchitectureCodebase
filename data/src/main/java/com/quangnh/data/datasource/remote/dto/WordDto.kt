package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class WordDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("metadata") var metadata: MetadataDto? = null,
    @SerializedName("results") var results: List<ResultDto>? = null,
    @SerializedName("word") var word: String? = null
)