package com.raju.androidcameraandmedia.basic.video_editor.component

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.raju.androidcameraandmedia.basic.video_player.component.PlayerSurface
import com.raju.androidcameraandmedia.basic.video_player.component.SurfaceType

@Composable
fun VideoPlayer(
    selectedVideo: Uri,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Box {
            val context = LocalContext.current

            val exoPlayer = remember {
                ExoPlayer.Builder(context).build().apply {
                    setMediaItem(MediaItem.fromUri(selectedVideo))
                    prepare()
                    playWhenReady = true
                    repeatMode = Player.REPEAT_MODE_ONE
                }
            }
            exoPlayer.volume = 0.5f

            DisposableEffect(Unit) {
                onDispose {
                    exoPlayer.release()
                }
            }

            PlayerSurface(
                exoPlayer,
                surfaceType = SurfaceType.SurfaceView,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }

}