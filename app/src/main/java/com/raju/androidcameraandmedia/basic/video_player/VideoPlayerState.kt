package com.raju.androidcameraandmedia.basic.video_player

data class VideoPlayerState(
    val videos: List<String> = emptyList(),
    val isPaused: Boolean = false,
    val audioNumber: Int = 0
)
