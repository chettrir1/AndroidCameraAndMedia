package com.raju.androidcameraandmedia.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raju.androidcameraandmedia.player.domain.PreparePlayerUseCase
import com.raju.androidcameraandmedia.player.domain.ReleasePlayerUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class VideoPlayerViewModel(
    private val preparePlayerUseCase: PreparePlayerUseCase,
    private val releasePlayerUseCase: ReleasePlayerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VideoPlayerState())
    val state = _state.asStateFlow()
    private var playbackJob: Job? = null
    private var playerViewJob: Job? = null

    private fun startTimeUpdate() {
        playbackJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _state.value.player?.let { player ->
                    val currentPosition = player.currentPosition
                    val duration = player.duration

                    _state.value = _state.value.copy(
                        elapsedTime = currentPosition, remainingTime = duration - currentPosition
                    )
                }
            }
        }
    }

    fun onAction(action: VideoPlayerAction) {
        viewModelScope.launch {
            when (action) {
                is VideoPlayerAction.LoadVideo -> {
                    val player = preparePlayerUseCase(action.url)
                    _state.value = _state.value.copy(
                        player = player, videoUrl = action.url, isPlaying = true
                    )
                    player.play()
                }

                VideoPlayerAction.Play -> {
                    _state.value.player?.play()
                    _state.value = _state.value.copy(isPlaying = true)
                    startTimeUpdate()
                }

                VideoPlayerAction.Pause -> {
                    _state.value.player?.pause()
                    _state.value = _state.value.copy(isPlaying = false)
                }

                VideoPlayerAction.Release -> {
                    releasePlayerUseCase()
                    _state.value = _state.value.copy(
                        player = null, isPlaying = false
                    )
                }

                VideoPlayerAction.FullScreen -> {
                    _state.value = _state.value.copy(
                        isFullScreen = !_state.value.isFullScreen
                    )
                }

                VideoPlayerAction.IsPlayerViewClicked -> {
                    _state.value = _state.value.copy(
                        isPlayerViewClicked = !_state.value.isPlayerViewClicked
                    )
                    if (_state.value.isPlayerViewClicked) {
                        startPlayerViewJob()
                    } else {
                        cancelPlayerViewJob()
                    }
                }

                is VideoPlayerAction.OrientationChange -> {
                    if (action.isLandscape && !_state.value.isFullScreen) {
                        _state.value = _state.value.copy(isFullScreen = true)
                    } else {
                        _state.value = _state.value.copy(isFullScreen = false)
                    }
                }
            }
        }
    }

    private fun startPlayerViewJob() {
        playerViewJob?.cancel()
        playerViewJob = viewModelScope.launch {
            delay(15000)
            _state.value = _state.value.copy(
                isPlayerViewClicked = false
            )
        }
    }

    private fun cancelPlayerViewJob() {
        playerViewJob?.cancel()
        playerViewJob = null
    }

    override fun onCleared() {
        super.onCleared()
        playbackJob?.cancel()
        onAction(VideoPlayerAction.Release)
    }
}