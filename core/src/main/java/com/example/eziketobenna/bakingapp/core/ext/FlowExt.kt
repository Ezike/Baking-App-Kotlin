package com.example.eziketobenna.bakingapp.core.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
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
