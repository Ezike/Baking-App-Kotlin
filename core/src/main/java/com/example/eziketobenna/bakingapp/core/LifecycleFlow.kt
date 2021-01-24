package com.example.eziketobenna.bakingapp.core

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LifecycleFlow<T>(
    private val flow: Flow<T>,
    lifecycleOwner: LifecycleOwner,
    private val action: suspend (value: T) -> Unit
) : DefaultLifecycleObserver {

    private var job: Job? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        job = flow.onEach(action).launchIn(owner.lifecycleScope)
    }

    override fun onStop(owner: LifecycleOwner) {
        job?.cancel()
        job = null
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        owner.lifecycle.removeObserver(this)
    }
}

fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    action: suspend (T) -> Unit
) = LifecycleFlow(this, lifecycleOwner, action)
