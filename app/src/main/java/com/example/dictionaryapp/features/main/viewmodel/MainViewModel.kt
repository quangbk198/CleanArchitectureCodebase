package com.example.dictionaryapp.features.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quangnh.core.base.utils.extension.onEachWrapper
import com.quangnh.core.base.viewmodel.BaseViewModel
import com.quangnh.domain.model.WordInfo
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
) : BaseViewModel() {

    private val _wordInfo: MutableLiveData<WordInfo> by lazy { MutableLiveData() }

    val wordInfo: LiveData<WordInfo> get() = _wordInfo

    override fun onDidBindViewModel() {

    }

    fun searchWord(word: String) {
        getWordInfo(word).onEachWrapper(
            this::setLoading,
            this::setError,
            viewModelScope
        ) { wordInfo ->
            _wordInfo.value = wordInfo
        }
    }

}