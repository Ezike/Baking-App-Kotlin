package com.example.eziketobenna.bakingapp.core.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {
    fun loadImage(view: ImageView, url: String, @DrawableRes fallBack: Int)
}
