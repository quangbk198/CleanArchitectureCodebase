package com.example.dictionaryapp.features.main.viewmodel

import com.quangnh.core.base.viewmodel.BaseViewModel
import com.quangnh.domain.usecase.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by quangnh
 * Date: 1/2/2023
 * Time: 11:39 PM
 * Project DictionaryApp
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
): BaseViewModel() {
    override fun onDidBindViewModel() {

    }
}