package com.example.eziketobenna.bakingapp.core.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun Fragment.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(
        requireContext(),
        id
    )
}

fun Fragment.onBackPress(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        onBackPressed = onBackPressed
    )
}

val Fragment.actionBar: androidx.appcompat.app.ActionBar?
    get() = (requireActivity() as AppCompatActivity).supportActionBar

var androidx.appcompat.app.ActionBar.visible: Boolean
    set(value) = if (value) this.show() else this.hide()
    get() = this.isShowing
