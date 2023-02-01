package com.quangnh.core.base.utils

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 5:04 PM
 * Project DictionaryApp
 */
sealed class Resource<T>(val data: T? = null, val throwable: Throwable? = null) {

    class Loading<T>(data: T? = null) : Resource<T>(data)

    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}
