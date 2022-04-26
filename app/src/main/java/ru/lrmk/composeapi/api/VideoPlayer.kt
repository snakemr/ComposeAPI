package ru.lrmk.composeapi.api

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun VideoPlayer(
    videoURI: String,
    startAt: Long = 0L,
    autoPlay: Boolean = true,
    showControls: Boolean = true
) {
    val position = rememberSaveable(key = videoURI) { mutableStateOf(startAt) }
    val context = LocalContext.current
    val exoPlayer = remember(videoURI) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoURI))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = autoPlay
            seekTo(position.value)
            prepare()
        }
    }
    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    useController = showControls
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )
    ) {
        onDispose {
            position.value = exoPlayer.currentPosition
            exoPlayer.release()
        }
    }
}