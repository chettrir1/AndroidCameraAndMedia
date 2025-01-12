package com.raju.androidcameraandmedia.basic.video_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.raju.androidcameraandmedia.R
import com.raju.androidcameraandmedia.basic.data.videos
import com.raju.androidcameraandmedia.player.presentation.component.PlayerSurface
import com.raju.androidcameraandmedia.player.presentation.component.SurfaceType
import com.raju.androidcameraandmedia.ui.theme.AndroidCameraAndMediaTheme

class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidCameraAndMediaTheme {

                var selectedVideo by remember {
                    mutableIntStateOf(0)
                }

                var isPaused by remember {
                    mutableStateOf(false)
                }

                Surface {
                    Box {
                        val context = LocalContext.current
                        val exoPlayer = remember {
                            ExoPlayer.Builder(context).build().apply {
                                setMediaItem(MediaItem.fromUri(videos[0]))
                                prepare()
                                playWhenReady = true
                                repeatMode = Player.REPEAT_MODE_ONE
                            }
                        }

                        PlayerSurface(
                            exoPlayer, surfaceType = SurfaceType.SurfaceView, modifier = Modifier
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(32.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    if (selectedVideo > 1) {
                                        selectedVideo--
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color = Color.Gray)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_prev),
                                    contentDescription = "previous",
                                    modifier = Modifier.size(32.dp),
                                )
                            }

                            IconButton(
                                onClick = {
                                    if (!isPaused) {
                                        exoPlayer.pause()
                                        isPaused = true
                                    } else {
                                        exoPlayer.play()
                                        isPaused = false
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color = Color.Gray)
                                    .padding(8.dp)

                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = if (!isPaused) {
                                            R.drawable.ic_play
                                        } else {
                                            R.drawable.ic_pause
                                        }
                                    ),
                                    contentDescription = "play",
                                    modifier = Modifier.size(60.dp),
                                )
                            }

                            IconButton(
                                onClick = {
                                    if (selectedVideo < 3) {
                                        selectedVideo++
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color = Color.Gray)
                                    .padding(8.dp)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_next),
                                    contentDescription = "next",
                                    modifier = Modifier.size(32.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}