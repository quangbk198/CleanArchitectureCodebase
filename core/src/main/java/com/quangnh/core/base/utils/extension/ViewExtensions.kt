package com.quangnh.core.base.utils.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by quangnh
 * Date: 29/1/2023
 * Time: 10:05 PM
 * Project DictionaryApp
 */

fun Context.showToastLong(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_LONG).show()
}

fun Context.showToastShort(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}