package com.raju.androidcameraandmedia.player.data

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class VideoPlayerRepositoryImpl(
    private val context: Context
) : VideoPlayerRepository {
    private var exoPlayer: ExoPlayer? = null

    override fun preparePlayer(uri: Uri): ExoPlayer {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
            repeatMode = Player.REPEAT_MODE_ONE
        }
        return exoPlayer!!

    }

    override fun releasePlayer() {
        exoPlayer?.release()
        exoPlayer = null
    }
}