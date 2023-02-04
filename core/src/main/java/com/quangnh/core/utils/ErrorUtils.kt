package com.quangnh.core.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.quangnh.core.R
import com.quangnh.core.model.ErrorResponse
import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorUtils {
    /**
     * Get error response from http response
     */
    private fun getError(throwable: Throwable?): ErrorResponse? {
        try {
            val gson = Gson()
            return when (throwable) {
                is UnknownHostException -> {
                    // Error lost internet
                    ErrorResponse(errorKey = ConstantsCore.ErrorKey.errorNoDefine)
                }

                is HttpException -> {
                    val errorResponse = gson.fromJson(
                        throwable.response()?.errorBody()?.string(),
                        ErrorResponse::class.java
                    )

                    val httpCode = throwable.code()
                    errorResponse.httpCode = httpCode

                    errorResponse
                }

                else -> {
                    ErrorResponse(errorKey = ConstantsCore.ErrorKey.errorNoDefine)
                }
            }
        } catch (e: Exception) {
            Log.e("TAG", "exception: ${e.message.toString()}")
            return null
        }
    }

    /**
     * Get error message by error key
     */
    fun errorMessage(throwable: Throwable?, context: Context): String {
        val errorResponse = getError(throwable)

        when (errorResponse?.errorKey) {
            ConstantsCore.ErrorKey.errorNoDefine -> {
                return context.getString(R.string.error_undefined)
            }
        }

        return context.getString(R.string.error_undefined)
    }
}