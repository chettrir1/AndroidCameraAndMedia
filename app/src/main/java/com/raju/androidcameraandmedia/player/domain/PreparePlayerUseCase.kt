package com.raju.androidcameraandmedia.player.domain

import androidx.media3.exoplayer.ExoPlayer
import com.raju.androidcameraandmedia.player.data.VideoPlayerRepository

class PreparePlayerUseCase(private val repository: VideoPlayerRepository) {
    operator fun invoke(url: String): ExoPlayer {
        return repository.preparePlayer(url)
    }
}