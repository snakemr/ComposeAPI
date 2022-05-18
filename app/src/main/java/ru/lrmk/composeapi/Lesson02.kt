package ru.lrmk.composeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Price

@Composable
fun Lesson02(cities: List<String>) {
    // cities - список названий городов Ставропольского края. Состоит из строк.

    Text("""Урок 2. Вывод небольшого списка
        
        Нажмите меню в верхнем углу, чтобы посмотреть пример.
        
        Для небольших списков достаточно обычного цикла, в котором будут выводится элементы списка.
        
        Цикл должен находиться внутри столбца Column{...} или строки Row{...}
        
        Цикл организуется так: 
        cities.forEach{...} 
        или так: 
        for (it in cities) {...}
        Внутри цикла it - это текущий его элемент (название города)
        
        Используйте текстовый элемент Text() для вывода названия с указанием Modifier.padding(5.dp) для отступов
        
        Используйте также элемент Divider() для рисования линий между строками.""".trimIndent())

}