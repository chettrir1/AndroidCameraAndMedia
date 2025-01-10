package com.raju.androidcameraandmedia.basic.video_player

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class VideoPlayerViewModel : ViewModel() {
    private val _state = MutableSharedFlow<VideoPlayerState>()
    var state = _state.asSharedFlow()
}