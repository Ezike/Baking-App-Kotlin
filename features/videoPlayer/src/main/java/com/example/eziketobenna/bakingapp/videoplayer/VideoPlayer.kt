package com.example.eziketobenna.bakingapp.videoplayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.example.eziketobenna.bakingpp.videoplayer.databinding.VideoPlayerBinding
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout

class VideoPlayer @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private var binding: VideoPlayerBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = VideoPlayerBinding.inflate(inflater, this, true)
    }

    fun init(owner: LifecycleOwner, playerState: VideoPlayerState) {
        VideoPlayerComponent(context, binding.playerView, playerState).let {
            owner.lifecycle.addObserver(it)
        }
    }

    var isFullScreen: Boolean
        set(value) {
            if (value) {
                hideSystemUi()
                expand()
            }
        }
        get() = this.systemUiVisibility == View.SYSTEM_UI_FLAG_FULLSCREEN

    private fun hideSystemUi() {
        binding.playerView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun expand() {
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        binding.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
    }
}
