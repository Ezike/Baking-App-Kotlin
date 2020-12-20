package com.example.eziketobenna.bakingapp.core.ext

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.SendChannel

fun <E> SendChannel<E>.safeOffer(value: E): Boolean = !isClosedForSend && try {
    offer(value)
} catch (e: CancellationException) {
    false
}
