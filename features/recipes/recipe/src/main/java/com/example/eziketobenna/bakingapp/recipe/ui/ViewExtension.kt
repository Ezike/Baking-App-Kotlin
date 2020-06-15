package com.example.eziketobenna.bakingapp.recipe.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

inline fun <reified R> Flow<R>.observe(lifecycleOwner: LifecycleOwner, crossinline action: (R) -> Unit) {
    this.onEach {
        action(it)
    }.launchIn(lifecycleOwner.lifecycleScope)
}
