package com.quangnh.domain.model

data class WordInfo(
    var id: Int? = null,
    var word: String? = null,
    var definitions: List<String>? = null,
    var timeStamp: Long? = null
)