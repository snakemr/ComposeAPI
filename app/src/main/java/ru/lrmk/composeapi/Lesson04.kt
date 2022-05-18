package ru.lrmk.composeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Price

@Composable
fun Lesson04(cities: List<String>) {
    // cities - список названий городов Российской Федерации. Состоит из 1000+ строк.

    Text("""Урок 4. Форма поиска строки в списке
        
        cities - очень длинный список строк. Сделаем поиск по списку для удобства пользователя.
        
        Программа будет состоять из поля ввода OutlinedTextField(...) и списка LazyColumn{...}
        
        Поместите их внутрь обычного Column{...}, чтобы расположить их один над другим.
        
        Но перед этим объявите переменную для поиска:
        var name by remember { mutableStateOf("") }
        
        Первым параметром элемента OutlinedTextField(..., ...) как раз и станет наш name.
        
        Вторым параметром будет блок { name = it }
        Он будет обновлять поиск по мере ввода данных.
        
        Ещё добавьте третий параметр label={Text("...")} чтобы отобразить в поле вашу подсказку.
        
        Внутри LazyColumn будет функция items(){}, перечисляющая элементы списка cities, но не все, а только отобранные поиском:
        items(cities.filter { name in it }) {...}
        
        Внутри функции отобразите строку it с модификатором отступа Modifier.padding(10.dp)
        
        И не забудьте про Divider()""".trimIndent() )

}