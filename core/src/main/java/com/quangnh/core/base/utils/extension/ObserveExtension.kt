package com.quangnh.core.base.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 10:04 PM
 * Project DictionaryApp
 */

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, data: (T) -> Unit) =
    liveData.observe(this, Observer(data))