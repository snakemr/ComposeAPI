package ru.lrmk.composeapi

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lrmk.composeapi.api.Api
import ru.lrmk.composeapi.api.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lesson08(movies: List<Movie>, onSelect: (Int)->Unit) {
    // movies - список фильмов Movie, где poster_path - имя файла постера к фильму,
    // id - идентификатор фильма для перехода по ссылке, vote_average - оценка зрителей
    // Функция Api.image(it.poster_path) получит для вас постер для картинки Image
    // onSelect - функция, которую надо вызвать при щелчке на постере, указав его id
    
    LazyVerticalGrid(GridCells.Adaptive(120.dp), Modifier.background(Color.Black)) {
        items(movies) {
            Box {
                Image(Api.image(it.poster_path), null,
                    Modifier
                        .size(200.dp)
                        .clickable {
                            onSelect(it.id)
                        })
                Text(it.vote_average.toString(),
                    Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color.Black,
                                    Color.Black,
                                    Color.Transparent
                                )
                            )
                        )
                        .padding(5.dp), color = Color.White)
            }
        }
    }
}

@Composable
fun MovieDetails(movie: Movie) {
    // movie - объект сведений о фильме, где poster_path - имя файла постера к фильму,
    // title - название фильма, overview - краткое описание, release_date - дата выпуска
    // production_companies - список компаний производителей
    // production_countries - список стран, участвующих в съёмках
    // Функция Api.bigImage(movie.poster_path) получит качественный постер для картинки Image
    // Функция Api.image(company.logo_path) получит логотип компании для картинки Image
    // Функция Api.flag(country.iso_3166_1) получит флаг страны для картинки Image
    // Списки production_companies, production_countries и строку logo_path нужно обязательно
    // проверить на неравенство null, прежде чем пытаться выводить их на экран

    Column {
        Text(movie.title, maxLines = 1, fontSize = 18.sp)
        Row {
            Image(Api.bigImage(poster = movie.poster_path), null, Modifier.weight(1f))
            LazyColumn(Modifier.padding(10.dp)) {
                if (movie.production_companies != null) items(movie.production_companies) {
                    if (it.logo_path != null)
                        Image(Api.image(poster = it.logo_path), null, Modifier.size(64.dp))
                }
                if (movie.production_countries != null) items(movie.production_countries) {
                    Image(Api.flag(countryCode = it.iso_3166_1), null, Modifier.size(64.dp))
                }
                item {
                    Text(Api.year(movie.release_date), Modifier.size(64.dp), textAlign = TextAlign.Center)
                }
            }
        }
        Text(movie.overview, Modifier.verticalScroll(rememberScrollState()))
    }
}