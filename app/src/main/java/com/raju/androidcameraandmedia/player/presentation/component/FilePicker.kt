package com.raju.androidcameraandmedia.player.presentation.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun FilePicker(
    onFilePicked: (Uri) -> Unit
) {

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                onFilePicked(uri)
            }
        }
    )

    Button(onClick = {
        filePickerLauncher.launch(arrayOf("video/*"))
    }) {
        Text(text = "Select Video File")
    }
}