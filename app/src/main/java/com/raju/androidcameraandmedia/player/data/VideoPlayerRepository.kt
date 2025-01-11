package com.raju.androidcameraandmedia.player.data

import androidx.media3.exoplayer.ExoPlayer

interface VideoPlayerRepository {
    fun preparePlayer(uri: String): ExoPlayer

    fun releasePlayer()
}