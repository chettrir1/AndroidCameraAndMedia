package com.raju.androidcameraandmedia.player.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raju.androidcameraandmedia.basic.video_player.component.PlayerSurface
import com.raju.androidcameraandmedia.basic.video_player.component.SurfaceType

@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PlayerSurface(
        state.player, surfaceType = SurfaceType.SurfaceView, modifier = Modifier
    )

}