package ru.lrmk.composeapi.api

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material.*
import ru.lrmk.composeapi.*

enum class Lessons(val Title: String, val Sample: @Composable (Api)->Unit) {
    L1("1. Спайдермен",  {var s by r{+false};Box{if(s)TP().Trailer()else TP().Poster();Switch(s,{s=it})}}),
    L2("Два", {}),
    L3("Три", {})
}

@Composable
fun DoLesson(lesson: Lessons, api: Api) = when(lesson) {
    Lessons.L1 -> Lesson01(TP())
    Lessons.L2 -> {}
    Lessons.L3 -> {}
}

private operator fun <T> T.unaryPlus() = mutableStateOf(this)

@Composable
private inline fun <T> r(calc: @DisallowComposableCalls () -> T): T = remember(calc)
