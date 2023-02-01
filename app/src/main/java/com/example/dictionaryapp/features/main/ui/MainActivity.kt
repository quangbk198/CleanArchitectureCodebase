package com.example.dictionaryapp.features.main.ui

import androidx.activity.viewModels
import com.example.dictionaryapp.databinding.ActivityMainBinding
import com.example.dictionaryapp.features.main.viewmodel.MainViewModel
import com.quangnh.core.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCommonViewLoaded() {

    }

    override fun addViewListener() {

    }
}