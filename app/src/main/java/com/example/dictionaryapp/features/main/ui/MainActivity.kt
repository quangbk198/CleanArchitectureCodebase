package com.example.dictionaryapp.features.main.ui

import androidx.activity.viewModels
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.features.main.viewmodel.MainViewModel
import com.quangnh.core.base.utils.extension.observe
import com.quangnh.core.base.utils.extension.safeOnClickListener
import com.quangnh.core.base.utils.extension.showToastShort
import com.quangnh.core.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCommonViewLoaded() {

    }

    override fun addViewListener() {
        binding.apply {
            btnSearch.safeOnClickListener {
                viewModel.searchWord(edtSearch.text.toString().trim())
            }
        }
    }

    override fun addDataObserver() {
        super.addDataObserver()

        viewModel.apply {
            observe(wordInfo) { word ->
                binding.tvDefinition.text = word.definitions?.get(0) ?: ""
            }
        }
    }
}