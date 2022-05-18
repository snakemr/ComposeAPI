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
    // Информацию предоставляет сайт https://www.themoviedb.org/ (возможны проблемы с доступом)

    Text("""Урок 7. Сетка эскизов с постерами фильмов
        
        movies - список текущих фильмов кинопроката. Каждый его элемент содержит poster_path - имя изображения.
          
        Вместо LazyColumn используем теперь LazyVerticalGrid(GridCells.Adaptive(120.dp)){...}
        
        Это экспериментальный пока что элемент отображения сетки. Ширину 120 мы указали как минимальную ширину эскиза.
        
        Ещё добавьте ему Modifier.background(Color.Black) - чёрный фон экрана.
        
        Как обычно, функция items(...){...} внутри сетки перечисляет элементы списка movies.
         
        Внутри - только картинка Image, первый параметр для неё берём из функции Api.image(it.poster_path)
        вторым будет "постер" или "" или null, третьим укажите размер эскиза Modifier.size(200.dp) 
        
        Можете поэкспериментировать с другими значениями размера и ширины - это будет влиять на количество столбцов сетки.
    """.trimIndent())

}