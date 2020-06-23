package com.example.eziketobenna.bakingapp.videoplayer

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.example.eziketobenna.bakingpp.videoplayer.databinding.VideoPlayerBinding
import com.google.android.exoplayer2.C

class VideoPlayer @JvmOverloads constructor(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private lateinit var playerComponent: VideoPlayerComponent
    private lateinit var playerState: VideoPlayerState
    private var binding: VideoPlayerBinding

    init {
        isSaveEnabled = true
        val inflater: LayoutInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = VideoPlayerBinding.inflate(inflater, this, true)
    }

    fun initVideoPlayer(owner: LifecycleOwner, videoUrl: String) {
        playerState = VideoPlayerState(videoUrl = videoUrl)
        playerComponent = VideoPlayerComponent(context, binding.playerView, playerState).also {
            owner.lifecycle.addObserver(it)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        if (!this::playerState.isInitialized) {
            return super.onSaveInstanceState()
        }
        val savedState = SavedState(super.onSaveInstanceState() ?: Bundle.EMPTY)
        return savedState.apply {
            playWhenReady = playerState.playWhenReady
            currentWindow = playerState.currentWindow
            playBackPosition = playerState.playBackPosition
            videoUrl = playerState.videoUrl
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            playerState = VideoPlayerState(
                playWhenReady = state.playWhenReady,
                currentWindow = state.currentWindow,
                playBackPosition = state.playBackPosition,
                videoUrl = state.videoUrl
            )
            playerComponent.setPlayerParams(playerState)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    internal class SavedState : BaseSavedState {

        var playWhenReady: Boolean = true
        var currentWindow: Int = C.INDEX_UNSET
        var playBackPosition: Long = 0
        var videoUrl: String? = null

        constructor(source: Parcel) : super(source) {
            playWhenReady = source.readByte().toInt() != 0
            currentWindow = source.readInt()
            playBackPosition = source.readLong()
            videoUrl = source.readString()
        }

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeByte(if (playWhenReady) 1 else 0)
            out.writeInt(currentWindow)
            out.writeLong(playBackPosition)
            out.writeString(videoUrl)
        }

        companion object CREATOR : Parcelable.Creator<VideoPlayerState> {
            override fun createFromParcel(parcel: Parcel): VideoPlayerState {
                return VideoPlayerState(parcel)
            }

            override fun newArray(size: Int): Array<VideoPlayerState?> {
                return arrayOfNulls(size)
            }
        }
    }
}
