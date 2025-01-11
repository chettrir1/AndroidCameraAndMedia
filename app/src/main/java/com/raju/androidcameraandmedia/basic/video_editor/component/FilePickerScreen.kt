package com.raju.androidcameraandmedia.basic.video_editor.component

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FilePickerScreen(modifier: Modifier = Modifier) {

    var selectedFileUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()

    ) {

        selectedFileUri?.let { uri ->
            VideoPlayer(
                uri,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilePicker(onFilePicked = { uri ->
                selectedFileUri = uri
            })

            Spacer(modifier = Modifier.height(16.dp))

            selectedFileUri?.let { uri ->
                Text(
                    text = "Selected File: $uri",
                    modifier = Modifier
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            } ?: Text(
                text = "No file Selected",
                modifier = Modifier
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}