package com.example.eziketobenna.bakingapp.core.ext

import android.util.Log

fun Any.logD(message: String) {
    Log.d(this::class.simpleName, message)
}

fun Any.logE(throwable: Throwable?) {
    Log.e(this::class.simpleName, "", throwable)
}

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred 😩"

inline fun String.notEmpty(action: (String) -> Unit) {
    if (this.isNotEmpty()) {
        action(this)
    }
}
