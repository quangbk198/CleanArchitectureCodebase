package com.quangnh.core.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.core.content.edit
import com.quangnh.core.BuildConfig
import java.util.Locale

/**
 * Created by quangnh
 * Date: 4/2/2023
 * Time: 5:02 PM
 * Project DictionaryApp
 */
object LocaleHelper {

    const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun onAttach(context: Context, defaultLanguage: String): Context {
        val lang = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getCurrentLanguage(context: Context): String {
        return getPersistedData(context, Locale.getDefault().language)
    }

    fun setLocale(context: Context, lang: String): Context {
        persist(context, lang)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, lang)
        }

        return updateResourcesLegacy(context, lang)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.apply {
            setLocale(locale)
            setLayoutDirection(locale)
        }

        return context.createConfigurationContext(config)
    }

    @SuppressWarnings("deprecation")
    @Suppress("DEPRECATION")
    private fun updateResourcesLegacy(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val resources = context.resources

        val config = resources.configuration
        config.locale = locale

        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        return context
    }

    private fun persist(context: Context, lang: String) {
        val preferences = context.getSharedPreferences(BuildConfig.SHARE_PREFERENCE_FOLDER, Context.MODE_PRIVATE)
        preferences.edit(commit = false) { putString(SELECTED_LANGUAGE, lang) }
    }

    private fun getPersistedData(context: Context, defaultLanguage: String): String {
        val preferences = context.getSharedPreferences(BuildConfig.SHARE_PREFERENCE_FOLDER, Context.MODE_PRIVATE)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage) ?: defaultLanguage
    }
}