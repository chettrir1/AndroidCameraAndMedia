package com.raju.androidcameraandmedia.basic.video_player

sealed interface VideoPlayerAction {
    data object OnPlayPauseClick : VideoPlayerAction
    data object OnNextClick : VideoPlayerAction
    data object OnPreviousClick : VideoPlayerAction
}