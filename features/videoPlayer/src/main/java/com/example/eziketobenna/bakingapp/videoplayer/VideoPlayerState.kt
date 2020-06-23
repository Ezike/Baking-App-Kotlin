package com.example.eziketobenna.bakingapp.videoplayer

import android.os.Parcel
import android.os.Parcelable
import com.google.android.exoplayer2.C

internal data class VideoPlayerState(
    var playWhenReady: Boolean = true,
    var currentWindow: Int = C.INDEX_UNSET,
    var playBackPosition: Long = 0,
    val videoUrl: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (playWhenReady) 1 else 0)
        parcel.writeInt(currentWindow)
        parcel.writeLong(playBackPosition)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
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
