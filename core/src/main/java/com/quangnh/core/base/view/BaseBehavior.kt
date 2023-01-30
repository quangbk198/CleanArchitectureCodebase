package com.quangnh.core.base.view

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:22 PM
 * Project DictionaryApp
 */
interface BaseBehavior {

    /**
     * Common view loaded and not depend device's orientation
     */
    fun onCommonViewLoaded()

    /**
     * On click listener of views
     */
    fun addViewListener()

    /**
     * Observe data from viewmodel to UI
     */
    fun addDataObserver()

    /**
     * Show/hide loading
     */
    fun onLoading(isLoading: Boolean = false)

    /**
     * Show error message
     */
    fun onError(error: Any)

    fun onBecomeVisible()

    fun onBecomeInvisible()
}