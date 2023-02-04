package com.quangnh.core.utils.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by quangnh
 * Date: 4/2/2023
 * Time: 10:48 AM
 * Project DictionaryApp
 */

/**
 * Delay function with Coroutine
 */
fun delayFunction(
    coroutineScope: CoroutineScope,
    timeDelay: Long,
    function: () -> Unit
) {
    coroutineScope.launch(Dispatchers.Main) {
        delay(timeMillis = timeDelay)
        function.invoke()
    }
}