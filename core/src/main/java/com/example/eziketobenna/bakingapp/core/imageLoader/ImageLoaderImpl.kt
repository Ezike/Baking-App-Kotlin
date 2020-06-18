package com.example.eziketobenna.bakingapp.core.imageLoader

import android.widget.ImageView
import coil.api.load
import coil.size.ViewSizeResolver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(true)
            size(ViewSizeResolver(view, false))
            listener(onError = { _, throwable ->
                throwable.printStackTrace()
            })
        }
    }
}
