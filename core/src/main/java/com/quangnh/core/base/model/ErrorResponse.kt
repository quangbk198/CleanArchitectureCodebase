package com.quangnh.core.base.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorKey") var errorKey: String? = null,
    @SerializedName("error") var message: String? = null,
    var httpCode: Int? = null
)
