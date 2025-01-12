package com.raju.androidcameraandmedia.util

import java.util.Locale

fun Long.formatTime(): String {
    val minutes = this / 1000 / 60
    val seconds = (this / 1000) % 60
    return String.format(Locale.CANADA, "%02d:%02d", minutes, seconds)

}