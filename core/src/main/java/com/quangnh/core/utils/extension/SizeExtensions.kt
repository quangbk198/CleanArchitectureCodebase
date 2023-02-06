package com.quangnh.core.utils.extension

import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup

/**
 * Created by quangnh
 * Date: 06/2/2023
 * Time: 09:30 AM
 * Project DictionaryApp
 */
fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun dpToPx(dp: Float): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

fun pxToDp(px: Float): Float {
    return px / (Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * Set width view
 */
fun View.setWidthView(width: Int) {
    val params: ViewGroup.LayoutParams = layoutParams
    params.width = width
    layoutParams = params
}

/**
 * Set height view
 */
fun View.setHeightView(height: Int) {
    val params: ViewGroup.LayoutParams = layoutParams
    params.height = height
    layoutParams = params
}