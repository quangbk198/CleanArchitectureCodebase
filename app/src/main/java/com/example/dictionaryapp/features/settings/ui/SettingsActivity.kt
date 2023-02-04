package com.example.dictionaryapp.features.settings.ui

import androidx.activity.viewModels
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.ActivitySettingsBinding
import com.example.dictionaryapp.features.settings.viewmodel.SettingsViewModel
import com.quangnh.core.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : BaseActivity<ActivitySettingsBinding, SettingsViewModel>(ActivitySettingsBinding::inflate) {

    override val viewModel: SettingsViewModel by viewModels()

    override fun onCommonViewLoaded() {
        // Setup title for actionbar
        title = getString(R.string.settings)
    }

    override fun addViewListener() {

    }
}