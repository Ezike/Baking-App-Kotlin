package com.example.eziketobenna.bakingapp.core.imageLoader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.api.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(view: ImageView, url: String, @DrawableRes fallBack: Int) {
        view.load(url) {
            crossfade(true)
            placeholder(fallBack)
            error(fallBack)
            size(ViewSizeResolver.invoke(view, false))
            scale(Scale.FIT)
        }
    }
}
