package com.berteodosio.seriemesmo.presentation.custom.logger

import android.util.Log

object AppLogger {

    fun i(tag: String, message: String, exception: Throwable? = null) {
        Log.e(tag, message, exception)
    }

    fun e(tag: String, message: String, exception: Throwable? = null) {
        Log.e(tag, message, exception)
    }
}