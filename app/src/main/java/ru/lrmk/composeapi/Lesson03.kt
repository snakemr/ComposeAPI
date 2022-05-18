package ru.lrmk.composeapi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lrmk.composeapi.api.Price

@Composable
fun Lesson03(price: List<Price>) {
    // price - список из 100 наименований товара.
    // Состоит из объектов Price, где .name - название товара (строка), .cost - цена (число с точкой)

    Text("""Урок 3. Длинный список 
        
        Для больших списков лучше использовать не цикл, а специальный "ленивый" столбец. 
        
        Это не только экономит ресурсы системы, но и автоматически подключает прокрутку списка.
        
        Как всегда, посмотрите пример и попробуйте повторить.
        
        Всё нужно заключить внутрь элемента LazyColumn{...}
        
        Внутри него будет находиться блок items(price){...} который будет перечислять элементы списка price.
        
        Если слово price подчёрнкуто, импортируйте через Alt+Enter.
        
        Внутри функции items(){...} неявным образом появляется переменная it - элемент списка price, где
        it.name - наименование (String), it.price - цена товара (Float, не забудьте перевести её .toString()).
        
        Чтобы расположить их по горизонтали используйте два Text внури блока Row(){...}
        
        В круглых скобках после Row укажите Modifier.padding(5.dp).height(42.dp) 
        для отступов и указания одинаковой высоты строк
         
        Первому Text добавьте ,Modifier.weight(1f) чтобы прижать цены вправо. 
        
        И не забудьте про Divider() после окончания Row(){...} """.trimIndent(), fontSize = 15.sp)

}