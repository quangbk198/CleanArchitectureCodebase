package com.quangnh.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by quangnh
 * Date: 31/1/2023
 * Time: 10:13 PM
 * Project DictionaryApp
 */
@Entity
data class WordInfoEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var word: String? = null,
    var definitions: List<String>? = null,
    var timeStamp: Long? = null
)