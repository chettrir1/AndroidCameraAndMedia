package com.raju.androidcameraandmedia.player.presentation

import android.net.Uri

sealed interface VideoPlayerAction {
    data class LoadVideo(val url: Uri) : VideoPlayerAction
    object Play : VideoPlayerAction
    object Pause : VideoPlayerAction
    object Release : VideoPlayerAction
    object FullScreen : VideoPlayerAction
}