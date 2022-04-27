package ru.lrmk.composeapi.api

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.lrmk.composeapi.*
import kotlin.experimental.ExperimentalTypeInference

enum class Lessons(val Title: String, val Sample: @Composable (Api)->Unit) {
    L1("1. Переключатель",  {var s by r{+false};Box(M.background(Color.Black)){if(s)TP().Trailer()else TP().Poster();Switch(s,{s=it})}}),
    L2("2. Список", {C{ps(l()){value=it.cities(2600000000000).map { it.city }}.value.forEach{Text(it,M.padding(5.dp));D()}}}),
    L3("3. Длинный список", {val p by ps(l<Price>()){value=it.price(count=100)};LC{items(p){R(M.padding(5.dp).height(42.dp)){Text(it.name, M.weight(1f));Text(it.cost.toString())};D()}}}),
    L4("4. Поиск строки", {val c by ps(l()){value=it.cities().map{it.city}};var name by r{+""};C{OutlinedTextField(name,{name=it},label={Text(text = "Поиск города...")});LC{items(c.filter{name in it}){Text(it,M.padding(10.dp));D()}}}}),
    L5("5. Информер", {val w by ps(Weather()){value=it.weather("Лермонтов")};C(M.background(Brush.linearGradient(listOf(Color.Cyan,Color.Blue)))){
            R{Text(w.name);Text(" 🌍 ${w.coord.lat}°, ${w.coord.lon}°, ")};R{Text("🌄 восход "+Api.time(w.sys.sunrise));Text(", 🌇 закат "+Api.time(w.sys.sunset))
            };Row(verticalAlignment=Alignment.CenterVertically) {Image(Api.icon(w.weather.firstOrNull()?.icon),"");Text("${w.main.temp} ℃ ",fontSize=42.sp)
            C{Text("${w.main.temp_min} ℃ – ${w.main.temp_max} ℃");Text("ощущается как ${w.main.feels_like} ℃")}};Text(w.weather.firstOrNull()?.description?:"",fontSize=24.sp)
            R{C{Text("💧влажность ${w.main.humidity}%");Text("📩 давление ${w.main.pressure} гПа");Text("🌥 облачность ${w.clouds.all}%");Text("☔ осадки ${w.rain.mm} мм/час");Text("👓 видимость ${w.visibility} м")}
            Column(M.weight(1f),horizontalAlignment=Alignment.CenterHorizontally){Text("⬆",M.rotate(-w.wind.deg.toFloat()), fontSize = 42.sp);Text("💨 ветер ${w.wind.speed} м/с ")}}}}),
    L6("6. Форма ввода", {val s = rememberCoroutineScope();var t by r{mutableStateOf(0)};var e by r{+""};var n by r{+""};var p by r{+""};Box(M.fillMaxSize()){if (t>0)
            Text("Успех!",M.align(Alignment.Center),color=Color.Gray,fontSize=18.sp) else C(M.align(Alignment.Center).width(IntrinsicSize.Min)){Text("АВТОРИЗАЦИЯ ПОЛЬЗОВАТЕЛЯ",color=Color.Gray,fontSize=18.sp);Text(e,color=Color.Red)
            OutlinedTextField(n,{n=it},label={Text("Имя пользователя")});OutlinedTextField(p,{p=it}, label={Text("Пароль")},visualTransformation=PasswordVisualTransformation())
            Button({s.launch{it.login(n,p).let {t=it.token;e=it.error?:""}}},Modifier.fillMaxWidth().clip(CircleShape)){Text("Вход в систему")}}}})
}

@Composable
fun DoLesson(lesson: Lessons, api: Api) = when(lesson) {
    Lessons.L1 -> Lesson01(TP())
    Lessons.L2 -> Lesson02(ps(l()) { value = api.cities(2600000000000).map { it.city } }.value)
    Lessons.L3 -> Lesson03(ps(l<Price>()) { value = api.price(count=100) }.value)
    Lessons.L4 -> Lesson04(ps(l()) { value = api.cities().map { it.city } }.value)
    Lessons.L5 -> Lesson05(ps(Weather()){ value = api.weather("Лермонтов") }.value)
    Lessons.L6 -> {
        val s = rememberCoroutineScope()
        var t by r { mutableStateOf(0) }
        var e by r { mutableStateOf<String?>("") }
        Lesson06(t, e ?: "") { n, p ->
            s.launch {
                api.login(n, p).let {
                    t = it.token
                    e = it.error
                }
            }
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
