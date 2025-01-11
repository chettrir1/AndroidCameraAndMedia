package com.raju.androidcameraandmedia.player.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.raju.androidcameraandmedia.R
import com.raju.androidcameraandmedia.basic.data.videos
import com.raju.androidcameraandmedia.basic.video_player.component.PlayerSurface
import com.raju.androidcameraandmedia.basic.video_player.component.SurfaceType
import com.raju.androidcameraandmedia.player.domain.PreparePlayerUseCase
import com.raju.androidcameraandmedia.player.domain.ReleasePlayerUseCase
import com.raju.androidcameraandmedia.ui.theme.AndroidCameraAndMediaTheme
import org.koin.androidx.compose.koinViewModel

class ExoplayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidCameraAndMediaTheme {
                Scaffold {
                    VideoPlayerScreen(
                        viewModel = koinViewModel(),
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}