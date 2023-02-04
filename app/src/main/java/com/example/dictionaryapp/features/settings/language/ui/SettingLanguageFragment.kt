package com.example.dictionaryapp.features.settings.language.ui

import androidx.fragment.app.activityViewModels
import com.example.dictionaryapp.R
import com.example.dictionaryapp.databinding.FragmentSettingLanguageBinding
import com.example.dictionaryapp.features.settings.viewmodel.SettingsViewModel
import com.quangnh.core.base.view.BaseFragment
import com.quangnh.core.utils.extension.safeOnClickListener
import com.quangnh.core.utils.extension.showToastShort
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by quangnh
 * Date: 4/2/2023
 * Time: 3:47 PM
 * Project DictionaryApp
 */
@AndroidEntryPoint
class SettingLanguageFragment :
    BaseFragment<FragmentSettingLanguageBinding, SettingsViewModel>(FragmentSettingLanguageBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = SettingLanguageFragment()
    }

    override val viewModel: SettingsViewModel by activityViewModels()

    override fun getTagFragment(): String = SettingLanguageFragment::class.java.simpleName

    override fun onCommonViewLoaded() {

    }

    override fun addViewListener() {
        binding.apply {
            rdVietnamese.safeOnClickListener {
                activity?.showToastShort("vn")
            }

            rdEnglish.safeOnClickListener {
                activity?.showToastShort("en")
            }
        }
    }
}