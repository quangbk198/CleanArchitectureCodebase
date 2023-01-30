package com.quangnh.core.resource.sharepref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.quangnh.core.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 4:57 PM
 * Project DictionaryApp
 */
class AppPreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppPreference {

    override fun getSharePreferences(): SharedPreferences =
        context.getSharedPreferences(BuildConfig.SHARE_PREFERENCE_FOLDER, Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        getSharePreferences().edit(commit = false) {
            putString(key, value)
        }
    }

    override fun getString(key: String, defaultValue: String): String =
        getSharePreferences().getString(key, defaultValue) ?: defaultValue


    override fun saveBoolean(key: String, value: Boolean) {
        getSharePreferences().edit(commit = false) {
            putBoolean(key, value)
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean =
        getSharePreferences().getBoolean(key, defaultValue)


    override fun saveInt(key: String, value: Int) {
        getSharePreferences().edit(commit = false) { putInt(key, value) }
    }

    override fun getInt(key: String, defaultValue: Int): Int =
        getSharePreferences().getInt(key, defaultValue)


    override fun saveFloat(key: String, value: Float) {
        getSharePreferences().edit(commit = false) { putFloat(key, value) }
    }

    override fun getFloat(key: String, defaultValue: Float): Float =
        getSharePreferences().getFloat(key, defaultValue)


    override fun saveLong(key: String, value: Long) {
        getSharePreferences().edit(commit = false) { putLong(key, value) }
    }

    override fun getLong(key: String, defaultValue: Long): Long =
        getSharePreferences().getLong(key, defaultValue)

}