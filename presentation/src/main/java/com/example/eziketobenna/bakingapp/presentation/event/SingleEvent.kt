package com.example.eziketobenna.bakingapp.presentation.event

import java.util.concurrent.atomic.AtomicBoolean

/**
 * Reference:
 * https://medium.com/@mdabrowski89/hi-thanks-for-the-grate-article-7659ed09ddd3
 */
abstract class SingleEvent<T>(private val argument: T) {

    private val isConsumed = AtomicBoolean(false)

    fun consume(action: (T) -> Unit) {
        if (!isConsumed.getAndSet(true)) {
            action.invoke(argument)
        }
    }
}
