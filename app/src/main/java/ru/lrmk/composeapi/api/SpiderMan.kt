package ru.lrmk.composeapi.api

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class TP(
    val posterURL: String = "3hLU5V1XDF0oHT9YUHvYC4j0Ix5.jpg",
    val tralerURL: String = "V0hagz_8L3M"
) {
    @Composable
    fun Poster() = Image(Api.bigImage(posterURL), contentDescription = "", Modifier.fillMaxSize())
    @Composable
    fun Trailer() = VideoPlayer(Api.video(tralerURL)) //YouTube(tralerURL)
}