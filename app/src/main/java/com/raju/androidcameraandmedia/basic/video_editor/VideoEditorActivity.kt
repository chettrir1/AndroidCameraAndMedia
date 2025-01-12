package com.raju.androidcameraandmedia.basic.video_editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.raju.androidcameraandmedia.basic.video_editor.component.FilePickerScreen
import com.raju.androidcameraandmedia.ui.theme.AndroidCameraAndMediaTheme

class VideoEditorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidCameraAndMediaTheme {
                Scaffold {
                    FilePickerScreen(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}