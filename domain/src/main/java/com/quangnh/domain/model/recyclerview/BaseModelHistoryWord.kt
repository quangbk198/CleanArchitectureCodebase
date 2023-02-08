package com.quangnh.domain.model.recyclerview

abstract class BaseModelHistoryWord {
    abstract fun getModelType(): Int

    companion object {
        const val TYPE_DATE = 0
        const val TYPE_WORD = 1
    }
}