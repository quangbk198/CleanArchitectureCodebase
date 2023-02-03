package com.quangnh.core.base.utils.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.quangnh.core.base.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.reflect.KFunction1

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 10:04 PM
 * Project DictionaryApp
 */

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, data: (T) -> Unit) =
    liveData.observe(this, Observer(data))

/**
 * onEach Resource value with kotlin flow
 */
fun <T> Flow<Resource<T>>.onEachWrapper(
    functionLoading: KFunction1<Boolean, Unit>,
    functionError: KFunction1<Throwable, Unit>,
    coroutineScope: CoroutineScope,
    onSuccess: (T?) -> Unit
) {
    this.onEach { result ->
        when (result) {
            is Resource.Success -> {
                functionLoading.invoke(false)
                onSuccess.invoke(result.data)
            }

            is Resource.Error -> {
                result.throwable?.let { functionError.invoke(it) }
            }

            is Resource.Loading -> {
                functionLoading.invoke(true)
            }
        }
    }.launchIn(coroutineScope)
}