package com.raju.androidcameraandmedia.player.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.raju.androidcameraandmedia.player.presentation.component.PlayerSurface
import com.raju.androidcameraandmedia.player.presentation.component.SurfaceType
import com.raju.androidcameraandmedia.player.presentation.component.VideoControls
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

    var isHidden by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = aspectRationModifier,
        contentAlignment = Alignment.BottomCenter
    ) {

        PlayerSurface(
            state.player,
            surfaceType = SurfaceType.SurfaceView,
            modifier = modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isHidden = !isHidden
                }
        )

        AnimatedVisibility(
            visible = isHidden,
            enter = fadeIn(
                animationSpec = tween(
                    300,
                    easing = EaseIn
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    300,
                    easing = EaseOut
                )
            )
        ) {
            VideoControls(
                state = state,
                onAction = { onAction }
            )
        }
    }
}