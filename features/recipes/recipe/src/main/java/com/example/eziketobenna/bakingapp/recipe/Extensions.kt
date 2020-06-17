package com.example.eziketobenna.bakingapp.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.eziketobenna.bakingapp.views.SimpleEmptyStateView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

inline fun <reified R> Flow<R>.observe(
    lifecycleOwner: LifecycleOwner,
    crossinline action: (R) -> Unit
) {
    this.onEach {
        action(it)
    }.launchIn(lifecycleOwner.lifecycleScope)
}

inline fun String.notEmpty(action: (String) -> Unit) {
    if (this.isNotEmpty()) {
        action(this)
    }
}

val SimpleEmptyStateView.clicks: Flow<Unit>
    get() = callbackFlow {
        val listener: () -> Unit = {
            offer(Unit)
            Unit
        }
        buttonClickListener = listener
        awaitClose { buttonClickListener = null }
    }.conflate()
