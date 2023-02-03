package com.quangnh.core.base.utils.extension

import android.view.View
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    waitMs: Long = 300L,
    coroutineScope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(waitMs)
            destinationFunction(param)
        }
    }
}

/**
 * Debounce click with Coroutine
 */
fun View.safeOnClickListener(onClickView: () -> Unit) {
    val lifecycleScope = ViewTreeLifecycleOwner.get(this)?.lifecycleScope
    lifecycleScope?.let { scope ->
        val handleClickEventsDebounced = debounce<Unit>(500L, scope) {
            onClickView.invoke()
        }

        this.setOnClickListener {
            handleClickEventsDebounced(Unit)
        }
    }
}