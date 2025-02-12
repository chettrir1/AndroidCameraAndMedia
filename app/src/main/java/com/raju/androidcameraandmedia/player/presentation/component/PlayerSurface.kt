package com.raju.androidcameraandmedia.player.presentation.component

import android.view.Surface
import android.widget.TabWidget
import androidx.compose.foundation.AndroidEmbeddedExternalSurface
import androidx.compose.foundation.AndroidExternalSurface
import androidx.compose.foundation.AndroidExternalSurfaceScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.exoplayer.ExoPlayer

sealed class SurfaceType {
    object TextureView : SurfaceType()
    object SurfaceView : SurfaceType()
}

fun calculateAspectRation(width: Int, height: Int): Float {
    return width.toFloat() / height.toFloat()
}

@Composable
fun PlayerSurface(
    player: ExoPlayer?,
    surfaceType: SurfaceType,
    modifier: Modifier = Modifier,

    ) {

    val onSurfaceCreated: (Surface) -> Unit = { surface ->
        player?.setVideoSurface(surface)
    }

    val onSurfaceDestroyed: () -> Unit = {
        player?.setVideoSurface(null)
    }

    val onSurfaceInitialized: AndroidExternalSurfaceScope.() -> Unit = {
        onSurface { surface, int1, int2 ->
            onSurfaceCreated(surface)
            surface.onDestroyed {
                onSurfaceDestroyed()
            }
        }
    }

    when (surfaceType) {
        SurfaceType.SurfaceView -> {
            AndroidExternalSurface(modifier = modifier, onInit = onSurfaceInitialized)
        }

        SurfaceType.TextureView ->
            AndroidEmbeddedExternalSurface(
                modifier = modifier,
                onInit = onSurfaceInitialized
            )
    }

}