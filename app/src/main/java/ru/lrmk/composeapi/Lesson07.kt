package ru.lrmk.composeapi

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Api
import ru.lrmk.composeapi.api.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lesson07(movies: List<Movie>) {
    // movies - список фильмов Movie, где poster_path - имя файла постера к фильму.
    // Функция Api.image(it.poster_path) получит для вас заполнитель для картинки Image

    LazyVerticalGrid(GridCells.Adaptive(120.dp), Modifier.background(Color.Black)) {
        items(movies) {
            Box {
                Image(Api.image(it.poster_path), null, Modifier.size(200.dp))
                Text(it.vote_average.toString(), Modifier.align(Alignment.TopEnd).background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Black,
                            Color.Black,
                            Color.Transparent
                        )
                    )).padding(5.dp), color = Color.White)
            }
        }
    }
}