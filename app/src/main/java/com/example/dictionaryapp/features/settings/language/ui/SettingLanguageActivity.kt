package com.example.dictionaryapp.features.settings.language.ui

import android.content.Intent.*
import androidx.activity.viewModels
import com.example.dictionaryapp.databinding.ActivitySettingLanguageBinding
import com.example.dictionaryapp.features.main.ui.MainActivity
import com.example.dictionaryapp.features.settings.viewmodel.SettingsViewModel
import com.example.dictionaryapp.utils.ConstantApp
import com.quangnh.core.base.view.BaseActivity
import com.quangnh.core.utils.LocaleHelper
import com.quangnh.core.utils.extension.safeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by quangnh
 * Date: 4/2/2023
 * Time: 3:47 PM
 * Project DictionaryApp
 */
@AndroidEntryPoint
class SettingLanguageActivity :
    BaseActivity<ActivitySettingLanguageBinding, SettingsViewModel>(ActivitySettingLanguageBinding::inflate) {

    override val viewModel: SettingsViewModel by viewModels()

    override fun onCommonViewLoaded() {
        when (LocaleHelper.getCurrentLanguage(this)) {
            ConstantApp.VI -> {
                binding.rdVietnamese.isChecked = true
            }

            ConstantApp.EN -> {
                binding.rdEnglish.isChecked = true
            }
        }
    }

    override fun addViewListener() {
        binding.apply {
            rdVietnamese.safeOnClickListener {
                setLanguage(ConstantApp.VI)
            }

            rdEnglish.safeOnClickListener {
                setLanguage(ConstantApp.EN)
            }
        }
    }

    /**
     * Set language
     */
    private fun setLanguage(lang: String) {
        LocaleHelper.setLocale(this, lang)
        openActivity(
            MainActivity::class.java,
            FLAG_ACTIVITY_CLEAR_TASK,
            FLAG_ACTIVITY_CLEAR_TOP,
            FLAG_ACTIVITY_NEW_TASK
        )
    }
}