package com.raju.androidcameraandmedia.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raju.androidcameraandmedia.player.domain.PreparePlayerUseCase
import com.raju.androidcameraandmedia.player.domain.ReleasePlayerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoPlayerViewModel(
    private val preparePlayerUseCase: PreparePlayerUseCase,
    private val releasePlayerUseCase: ReleasePlayerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VideoPlayerState())
    val state = _state.asStateFlow()

    fun onAction(action: VideoPlayerAction) {
        viewModelScope.launch {
            when (action) {
                is VideoPlayerAction.LoadVideo -> {
                    val player = preparePlayerUseCase(action.url)
                    _state.value = _state.value.copy(
                        player = player,
                        videoUrl = action.url,
                        isPlaying = true
                    )
                    player.play()
                }

                VideoPlayerAction.Play -> {
                    _state.value.player?.play()
                    _state.value = _state.value.copy(isPlaying = true)
                }

                VideoPlayerAction.Pause -> {
                    _state.value.player?.pause()
                    _state.value = _state.value.copy(isPlaying = false)
                }

                VideoPlayerAction.Release -> {
                    releasePlayerUseCase()
                    _state.value = _state.value.copy(
                        player = null,
                        isPlaying = false
                    )

                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        onAction(VideoPlayerAction.Release)
    }
}