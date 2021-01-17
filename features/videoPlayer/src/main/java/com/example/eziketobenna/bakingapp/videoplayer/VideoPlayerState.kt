package com.example.eziketobenna.bakingapp.videoplayer

import android.os.Parcelable
import com.google.android.exoplayer2.C
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoPlayerState(
    internal var playWhenReady: Boolean = true,
    internal var currentWindow: Int = C.INDEX_UNSET,
    internal var playBackPosition: Long = 0,
    internal var videoUrl: String? = null
) : Parcelable {

    /**
     * reset the playback position only if the video url changes
     */
    fun checkAndSet(url: String): VideoPlayerState =
        if (videoUrl == url) {
            this
        } else {
            this.apply {
                videoUrl = url
                playBackPosition = 0
                currentWindow = C.INDEX_UNSET
            }
        }
}
