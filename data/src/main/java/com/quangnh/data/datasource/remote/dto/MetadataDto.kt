package com.quangnh.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class MetadataDto(
    @SerializedName("operation") var operation: String? = null,
    @SerializedName("provider") var provider: String? = null,
    @SerializedName("schema") var schema: String? = null
)