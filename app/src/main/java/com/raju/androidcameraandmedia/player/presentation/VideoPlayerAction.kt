package com.raju.androidcameraandmedia.player.presentation

sealed interface VideoPlayerAction {
    data class LoadVideo(val url: String) : VideoPlayerAction
    object Play : VideoPlayerAction
    object Pause : VideoPlayerAction
    object Release : VideoPlayerAction
}