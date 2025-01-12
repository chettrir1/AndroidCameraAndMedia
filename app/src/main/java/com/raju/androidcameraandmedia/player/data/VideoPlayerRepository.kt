package com.raju.androidcameraandmedia.player.data

import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer

interface VideoPlayerRepository {
    fun preparePlayer(uri: Uri): ExoPlayer

    fun releasePlayer()
}