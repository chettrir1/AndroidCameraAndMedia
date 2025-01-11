package com.raju.androidcameraandmedia.player.presentation

import androidx.media3.exoplayer.ExoPlayer

data class VideoPlayerState(
    val isPlaying: Boolean = false,
    val videoUrl: String = "",
    val player: ExoPlayer? = null
)
