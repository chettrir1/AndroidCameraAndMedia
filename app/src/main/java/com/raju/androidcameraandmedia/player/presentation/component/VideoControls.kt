package com.raju.androidcameraandmedia.player.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raju.androidcameraandmedia.R
import com.raju.androidcameraandmedia.player.presentation.VideoPlayerAction
import com.raju.androidcameraandmedia.player.presentation.VideoPlayerState
import com.raju.androidcameraandmedia.ui.theme.AndroidCameraAndMediaTheme
import com.raju.androidcameraandmedia.util.formatTime

@Composable
fun VideoControls(
    state: VideoPlayerState,
    onAction: (VideoPlayerAction) -> Unit
) {

    val duration = state.player?.duration ?: 1L
    val elapsedTime = state.elapsedTime ?: 1L
    val remainingTime = state.remainingTime ?: 1L

    val elapsedTimeFormatted = elapsedTime.formatTime()
    val remainingTimeFormatted = remainingTime.formatTime()

    println("elapsedTime $elapsedTimeFormatted")
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(color = Color.Gray.copy(alpha = 0.5f))
            .padding(start = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = elapsedTimeFormatted,
                color = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )

            CustomLinearProgressIndicator(
                progress = (elapsedTime.toFloat() / duration.toFloat()).coerceIn(0f, 1f),
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
            )

            Text(
                text = "-$remainingTimeFormatted",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )

            IconButton(
                onClick = {
                    onAction(VideoPlayerAction.FullScreen)
                },
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

}

@Preview(showBackground = true)
@Composable
private fun VideoControlsPreview() {

    AndroidCameraAndMediaTheme {
        VideoControls(
            state = VideoPlayerState(),
            onAction = {

            }
        )
    }
}