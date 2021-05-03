package com.berteodosio.seriemesmo.presentation.custom.logger

import android.util.Log

object AppLogger {

    fun d(tag: String, message: String, exception: Throwable? = null) {
        Log.d(tag, message, exception)
    }

    fun i(tag: String, message: String, exception: Throwable? = null) {
        Log.i(tag, message, exception)
    }

    fun e(tag: String, message: String, exception: Throwable? = null) {
        Log.e(tag, message, exception)
    }
}