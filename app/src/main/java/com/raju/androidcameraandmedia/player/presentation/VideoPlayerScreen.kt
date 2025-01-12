package com.raju.androidcameraandmedia.player.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raju.androidcameraandmedia.R
import com.raju.androidcameraandmedia.player.presentation.component.PlayerSurface
import com.raju.androidcameraandmedia.player.presentation.component.SurfaceType
import com.raju.androidcameraandmedia.player.presentation.component.calculateAspectRation

@Composable
fun VideoPlayerScreen(
    onAction: (VideoPlayerAction) -> Unit,
    state: VideoPlayerState,
    modifier: Modifier = Modifier
) {

    val aspectRatio = calculateAspectRation(1920, 1080)

    val aspectRationModifier = modifier
        .fillMaxSize()
        .aspectRatio(aspectRatio)

    Box(
        modifier = aspectRationModifier
    ) {

        PlayerSurface(
            state.player,
            surfaceType = SurfaceType.SurfaceView,
            modifier = modifier
        )

        IconButton(
            onClick = {
                onAction(VideoPlayerAction.FullScreen)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                painter = if (state.isFullScreen)
                    painterResource(R.drawable.ic_fullscreen_exit)
                else painterResource(
                    R.drawable.ic_fullscreen
                ),
                contentDescription = "Full Screen",
                modifier = Modifier
                    .size(24.dp)
            )

        }
    }
}