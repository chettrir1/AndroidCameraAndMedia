package com.raju.androidcameraandmedia.player.domain

import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import com.raju.androidcameraandmedia.player.data.VideoPlayerRepository

class PreparePlayerUseCase(private val repository: VideoPlayerRepository) {
    operator fun invoke(uri: Uri): ExoPlayer {
        return repository.preparePlayer(uri)
    }
}