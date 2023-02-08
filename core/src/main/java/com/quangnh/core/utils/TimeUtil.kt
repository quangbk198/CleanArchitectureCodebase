package com.quangnh.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object TimeUtil {
    fun long2String(time: Long?, format: String?): String {
        val simpleDateFormat = SimpleDateFormat(format)
        return try {
            val date = time?.let { Date(it) }
            simpleDateFormat.format(date)
        } catch (e: Exception) {
            ConstantsCore.EMPTY_STRING
        }
    }
}