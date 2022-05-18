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

    Text("""Урок 8. Подробные сведения о фильме
        
        Эта часть почти повторяет прошлый урок, можно скопировать оттуда. Два отличия:
        
        По щелчку на картинке должны открываться сведения о фильме. Добавьте к Modifier картинки
        .clickable{...} который должен вызвать функцию onSelect(...) с указанием it.id фильма
        
        Также покажем поверх постера зрительский рейтинг. Для этого:
        сначала внесите ваш Image внутрь нового блока Box{...}. Это позволяет отображать элементы один поверх другого.
        добавьте после картинки Text, в котором отобразите it.vote_average (это не строка, надо преобразовать)
        
        Чтобы перенести рейтинг в правый верхний угол постера добавьте этому элементу
        Modifier.align(Alignment.TopEnd)
        
        Чтобы цифры не сливались с фоном, добавьте к 
        Modifier подложку .background(...), в ней можно указать или сплошной цвет, или полупрозрачный
        Brush.radialGradient(
        listOf(Color.Black,Color.Black,Color.Transparent))
        
        Еще добавьте к этому же Modifier отступы: 
        .padding(5.dp)
        
        Если надо, задайте цвет цифр: color = Color.White""".trimIndent())

}

@Composable
fun MovieDetails(movie: Movie) {
    // movie - объект сведений о фильме, где poster_path - имя файла постера к фильму,
    // title - название фильма, overview - краткое описание, release_date - дата выпуска
    // production_companies - список компаний производителей
    // production_countries - список стран, участвующих в съёмках
    // Функция Api.bigImage(movie.poster_path) получит качественный постер для картинки Image
    // Функция Api.image(it.logo_path) получит логотип компании для картинки Image
    // Функция Api.flag(it.iso_3166_1) получит флаг страны для картинки Image
    // Списки production_companies, production_countries и строку logo_path нужно обязательно
    // проверить на неравенство null, прежде чем пытаться выводить их на экран

    Text("""Детальные сведения о фильме id=${movie.id}
        
        movie - объект сведений о фильме (подробности в комментарии). Все элементы - внутри Column{...}
        
        Первая строка - Text для вывода названия,
        затем - Row{...} для размещения по горизонтали постера и списка компаний,
        последняя строка - Text для вывода описания фильма, для прокрутки длинного текста используйте
        Modifier.verticalScroll(rememberScrollState())
        
        Внутри Row поместите Image, постер для которой вам даст Api.bigImage(movie.poster_path),
        и LazyColumn. Чотбы Image растянулся почти на всю ширину, укажите ему Modifier.weight(1f), а столбцу задайте оступы Modifier.padding(10.dp)
        
        Внутри LazyColumn - три набора элементов:
        если production_companies не null, то items(){}, перечисляющая компании,
        если production_countries не null, то items(){}, перечисляющая страны,
        одиночный item{...}, в нём - Text для вывода года выпуска Api.year(movie.release_date), по центру.
        
        В первом items(){...}: Если it.logo_path не null, то Image для вывода постера it.logo_path с помощью Api.image(...)
        Во втором: Image для вывода флага страны it.iso_3166_1 с помощью Api.flag(...)
        В обеих Image добавьте модификатор размера логотипа Modifier.size(64.dp)""".trimIndent())

}