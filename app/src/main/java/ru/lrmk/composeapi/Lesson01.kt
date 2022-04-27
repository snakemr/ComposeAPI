package ru.lrmk.composeapi

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.lrmk.composeapi.api.TP

@Composable
fun Lesson01(spiderMan: TP) {
    // spiderMan - тренировочный объект, который может отображать свой .Trailer() либо .Poster()
    Text("""
    Урок 1. Вывод на экран по условию  
      
    Нужно вывести на экран либо трейлер, либо 
    постер фильма, который дан в параметрах этой функции. 
    
    Это происходит в зависимости от положения переключателя (Switch).
    
    Нажмите меню в верхнем углу, чтобы посмотреть пример.
    
    Объявление логической переменной для переключения:
    var switch by remember { mutableStateOf(false)  }
    
    По условию (if) выводится либо spiderMan.Trailer(), либо spiderMan.Poster()
    
    Переключатель Switch отображает значение переменной switch и её же переключает: { switch = it }
    
    Чтобы переключатель был не посередине экрана, заключите всё внутрь блока Box { ... }
    
    Чтобы фон был чёрным, установите для Box параметр Modifier.background(Color.Black)
    
    """.trimIndent())
}