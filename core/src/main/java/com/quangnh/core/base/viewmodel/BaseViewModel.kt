package com.quangnh.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quangnh.core.base.utils.SingleLiveEvent
import com.quangnh.core.resource.sharepref.AppPreference

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:02 PM
 * Project DictionaryApp
 */
abstract class BaseViewModel : ViewModel() {

    lateinit var appPreference: AppPreference

    open val errorThrowable = SingleLiveEvent<Throwable>()
    open val loadingState = SingleLiveEvent<Boolean>()

    open var isInitialized: Boolean = false

    fun setLoading(isLoading: Boolean) {
        loadingState.value = isLoading
    }

    fun setError(error: Throwable) {
        errorThrowable.value = error
    }

    internal fun initViewModel(
        appPreference: AppPreference
    ) {
        this.appPreference = appPreference
        isInitialized = true
    }

    abstract fun onDidBindViewModel()
}