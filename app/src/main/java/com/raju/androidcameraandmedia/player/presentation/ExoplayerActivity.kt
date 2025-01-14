package com.raju.androidcameraandmedia.player.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raju.androidcameraandmedia.player.presentation.component.FilePicker
import com.raju.androidcameraandmedia.ui.theme.AndroidCameraAndMediaTheme
import org.koin.androidx.compose.koinViewModel

class ExoplayerActivity : ComponentActivity() {
    private lateinit var viewModel: VideoPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            viewModel = koinViewModel()
            AndroidCameraAndMediaTheme {
                val state by viewModel.state.collectAsStateWithLifecycle()

                requestedOrientation = if (state.isFullScreen) {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }

                Scaffold {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(if (!state.isFullScreen) it else PaddingValues(0.dp))
                    ) {
                        if (state.videoUrl == null) {
                            FilePicker(onFilePicked = { uri ->
                                viewModel.onAction(VideoPlayerAction.LoadVideo(uri))
                            })
                        }

                        if (state.videoUrl != null) {
                            VideoPlayerScreen(
                                state = state,
                                onAction = viewModel::onAction,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        viewModel.onAction(VideoPlayerAction.Pause)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        if (::viewModel.isInitialized) {
            viewModel.onAction(VideoPlayerAction.Play)
        }
    }

}