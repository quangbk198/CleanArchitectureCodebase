package com.quangnh.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:02 PM
 * Project DictionaryApp
 */
abstract class BaseViewModel : ViewModel() {

    open val errorState = MutableLiveData<String>()
    open val loadingState = MutableLiveData<Boolean>()

    open var isInitialized: Boolean = false

    fun setLoading(isLoading: Boolean) {
        loadingState.value = isLoading
    }

    fun setError(error: Throwable) {
        errorState.value = error.message
    }

    internal fun init() {
        isInitialized = true
    }

    abstract fun onDidBindViewModel()
}