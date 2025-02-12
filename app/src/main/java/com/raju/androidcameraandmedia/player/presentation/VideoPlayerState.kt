package com.raju.androidcameraandmedia.player.presentation

import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer

data class VideoPlayerState(
    val isPlaying: Boolean = false,
    val videoUrl: Uri? = null,
    val player: ExoPlayer? = null,
    val isFullScreen: Boolean = false,
    val elapsedTime: Long? = 0L,
    val remainingTime: Long? = 0L,
    val isPlayerViewClicked: Boolean = false
)
