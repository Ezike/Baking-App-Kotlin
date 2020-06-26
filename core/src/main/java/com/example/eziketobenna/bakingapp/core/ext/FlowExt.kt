package com.example.eziketobenna.bakingapp.core.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun <reified R> Flow<R>.observe(
    lifecycleOwner: LifecycleOwner,
    crossinline action: (R) -> Unit
) {
    this.onEach {
        action(it)
    }.launchIn(lifecycleOwner.lifecycleScope)
}

fun <E> SendChannel<E>.safeOffer(value: E): Boolean = !isClosedForSend && try {
    offer(value)
} catch (e: CancellationException) {
    false
}
