package com.quangnh.domain.model

import com.quangnh.domain.model.recyclerview.BaseModelHistoryWord

data class WordInfo(
    var id: Int? = null,
    var word: String? = null,
    var definitions: List<String>? = null,
    var timeStamp: Long? = null
): BaseModelHistoryWord() {
    override fun getModelType(): Int = TYPE_WORD
}