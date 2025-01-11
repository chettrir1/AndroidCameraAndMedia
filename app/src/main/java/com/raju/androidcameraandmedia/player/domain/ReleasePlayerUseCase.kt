package com.raju.androidcameraandmedia.player.domain

import com.raju.androidcameraandmedia.player.data.VideoPlayerRepository

class ReleasePlayerUseCase(private val repository: VideoPlayerRepository) {
    operator fun invoke() {
        repository.releasePlayer()
    }
}