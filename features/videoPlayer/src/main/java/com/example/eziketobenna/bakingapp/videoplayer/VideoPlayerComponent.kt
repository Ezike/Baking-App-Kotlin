package com.example.eziketobenna.bakingapp.videoplayer

import android.content.Context
import android.net.Uri
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlin.math.max

internal class VideoPlayerComponent(
    private val context: Context,
    private val playerView: PlayerView,
    private val playerState: VideoPlayerState
) : DefaultLifecycleObserver {

    private var player: SimpleExoPlayer? = null

    private val simpleExoPlayer: SimpleExoPlayer
        get() = ExoPlayerFactory.newSimpleInstance(context,
            DefaultTrackSelector().apply {
                setParameters(buildUponParameters().setMaxVideoSizeSd())
            })

    private fun initPlayer() {
        if (player == null) {
            player = simpleExoPlayer
        }
        playerView.player = player
        setPlayerParams(playerState)
    }

    private fun setPlayerParams(state: VideoPlayerState) {
        state.videoUrl?.let {
            val uri: Uri = Uri.parse(it)
            val mediaSource: MediaSource = buildMediaSource(uri)
            player?.playWhenReady = state.playWhenReady
            val hasResumePosition: Boolean = state.currentWindow != C.INDEX_UNSET
            if (hasResumePosition) {
                player?.seekTo(state.currentWindow, state.playBackPosition)
            }
            player?.prepare(mediaSource, false, false)
        }
    }

    private fun releasePlayer() {
        updateStartPosition()
        disposePlayer()
    }

    internal fun disposePlayer() {
        player?.release()
        player = null
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "bakingApp")
        )
        return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    private fun updateStartPosition() {
        playerState.playWhenReady = player?.playWhenReady ?: true
        playerState.currentWindow = player?.currentWindowIndex ?: C.INDEX_UNSET
        playerState.playBackPosition = max(0, player?.contentPosition ?: 0)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        if (Util.SDK_INT > 23) {
            initPlayer()
            playerView.onResume()
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (Util.SDK_INT <= 23) {
            initPlayer()
            playerView.onResume()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        if (Util.SDK_INT <= 23) {
            playerView.onPause()
            releasePlayer()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (Util.SDK_INT > 23) {
            playerView.onPause()
            releasePlayer()
        }
    }
}
