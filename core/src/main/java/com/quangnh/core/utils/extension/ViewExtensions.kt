package com.quangnh.core.utils.extension

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

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

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.loadImageDrawable(id: Int) {
    Glide.with(this.context).load(id).fitCenter().into(this)
}

/**
 * Set width and height to view
 */
fun View.setWidthHeightToView(width: Int, height: Int) {
    val params: ViewGroup.LayoutParams = layoutParams
    params.height = height
    params.width = width
    layoutParams = params
}
