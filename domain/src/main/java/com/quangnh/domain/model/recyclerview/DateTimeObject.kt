package com.quangnh.domain.model.recyclerview

data class DateTimeObject(var date: String): BaseModelHistoryWord() {
    override fun getModelType(): Int = TYPE_DATE
}
