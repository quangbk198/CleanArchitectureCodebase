package com.quangnh.core.resource.sharepref

import android.content.SharedPreferences

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 4:57 PM
 * Project DictionaryApp
 */
interface AppPreference {

    fun getSharePreferences(): SharedPreferences

    fun saveString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String

    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun saveInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int): Int

    fun saveFloat(key: String, value: Float)
    fun getFloat(key: String, defaultValue: Float): Float

    fun saveLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long): Long

}