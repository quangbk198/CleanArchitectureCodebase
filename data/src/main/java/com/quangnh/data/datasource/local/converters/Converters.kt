package com.quangnh.data.datasource.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionaryappcleanarchitecture.data.util.JsonParser
import com.google.gson.reflect.TypeToken

/**
 * Created by quangnh
 * Date: 31/1/2023
 * Time: 11:22 PM
 * Project DictionaryApp
 */
@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromDefinitionJson(json: String): List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toDefinitionJson(definition: List<String>): String {
        return jsonParser.toJson(
            definition,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: "[]"
    }
}