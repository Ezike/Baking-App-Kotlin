package com.example.eziketobenna.bakingapp.core.imageLoader

import android.widget.ImageView
import coil.api.load
import com.example.eziketobenna.bakingapp.core.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoaderImpl @Inject constructor() : ImageLoader {
    override fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(true)
            placeholder(R.drawable.cheese_cake)
            listener(onError = { _, throwable ->
                throwable.printStackTrace()
            })
        }
    }
}
