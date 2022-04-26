package ru.lrmk.composeapi.api

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.*
import kotlin.experimental.ExperimentalTypeInference

enum class Lessons(val Title: String, val Sample: @Composable (Api)->Unit) {
    L1("1. Постер или трейлер",  {var s by r{+false};Box(M.background(Color.Black)){if(s)TP().Trailer()else TP().Poster();Switch(s,{s=it})}}),
    L2("2. Список", {C{ps(l()){value=it.cities(2600000000000).map { it.city }}.value.forEach{Text(it,M.padding(10.dp));D()}}}),
    L3("3. Длинный список", {val p by ps(l<Price>()){value=it.price(count=100)};LC{items(p){R(M.padding(10.dp).height(42.dp)){Text(it.name, M.weight(1f));Text(it.cost.toString())};D()}}}),
    L4("4", {})
}

@Composable
fun DoLesson(lesson: Lessons, api: Api) = when(lesson) {
    Lessons.L1 -> Lesson01(TP())
    Lessons.L2 -> Lesson02(ps(l()) { value = api.cities(2600000000000).map { it.city } }.value)
    Lessons.L3 -> Lesson03(ps(l<Price>()) { value = api.price(count=100) }.value)
    Lessons.L4 -> Column {
        ps(l(), { value = api.cities(2600000000000).map { it.city } }).value.forEach {
            Text(it)
        }
    }
}

private operator fun <T> T.unaryPlus() = mutableStateOf(this)

@Composable
private inline fun <T> r(calc: @DisallowComposableCalls () -> T): T = remember(calc)

@SuppressLint("ProduceStateDoesNotAssignValue") @OptIn(ExperimentalTypeInference::class)
@Composable private fun <T> ps(initialValue: T,
    @BuilderInference producer: suspend ProduceStateScope<T>.() -> Unit
): State<T> = produceState(initialValue, producer)

private fun <T> l(): List<T> = listOf()

@Composable private fun D() = Divider()
@Composable private fun LC(content: LazyListScope.() -> Unit) = LazyColumn(content = content)
@Composable private fun R(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) = Row(modifier, content = content)
@Composable private fun C(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) = Column(modifier, content = content)
private val M = Modifier
