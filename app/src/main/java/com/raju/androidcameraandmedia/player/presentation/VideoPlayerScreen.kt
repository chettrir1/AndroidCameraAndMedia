package com.raju.androidcameraandmedia.player.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

    Box(
        modifier = aspectRationModifier
            .background(color = Color.Black),
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
                    onAction(VideoPlayerAction.IsPlayerViewClicked)
                }
        )

        AnimatedVisibility(
            visible = state.isPlayerViewClicked,
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
            ),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Adjust alpha for darkness
            )
        }

        AnimatedVisibility(
            visible = state.isPlayerViewClicked,
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
            ),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Row {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_media_previous),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

                IconButton(
                    onClick = {
                        if (state.isPlaying) {
                            onAction(VideoPlayerAction.Pause)
                        } else {
                            onAction(VideoPlayerAction.Play)
                        }
                    }
                ) {
                    Icon(
                        painter = if (state.isPlaying)
                            painterResource(android.R.drawable.ic_media_pause)
                        else painterResource(android.R.drawable.ic_media_play),
                        tint = Color.White,
                        contentDescription = "Play",
                        modifier = Modifier
                            .size(56.dp)
                    )
                }

                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        painter = painterResource(android.R.drawable.ic_media_next),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = state.isPlayerViewClicked,
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
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            VideoControls(
                state = state,
                onAction = { onAction }
            )
        }
    }
}