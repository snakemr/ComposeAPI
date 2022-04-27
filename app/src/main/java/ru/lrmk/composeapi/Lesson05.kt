package ru.lrmk.composeapi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import ru.lrmk.composeapi.api.Api
import ru.lrmk.composeapi.api.Weather

@Composable
fun Lesson05(weather: Weather) {
    // weather - объект, содержащий всю информацию о текущей погоде
    Column(Modifier.background(Brush.linearGradient(listOf(Color.Cyan, Color.Blue)))) {
        Row {
            Text(weather.name)
            Text(" 🌍 ${weather.coord.lat}°, ${weather.coord.lon}°, ")
        }
        Row {
            Text("🌄 восход " + Api.time(weather.sys.sunrise))
            Text(", 🌇 закат " + Api.time(weather.sys.sunset))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(Api.icon(weather.weather.firstOrNull()?.icon), "")
            Text("${weather.main.temp} ℃ ", fontSize = 42.sp)
            Column {
                Text("${weather.main.temp_min} ℃ – ${weather.main.temp_max} ℃")
                Text("ощущается как ${weather.main.feels_like} ℃")
            }
        }
        Text(weather.weather.firstOrNull()?.description ?: "", fontSize = 24.sp)
        Row {
            Column {
                Text("💧влажность ${weather.main.humidity}%")
                Text("📩 давление ${weather.main.pressure} гПа")
                Text("🌥 облачность ${weather.clouds.all}%")
                Text("☔ осадки ${weather.rain.mm} мм/час")
                Text("👓 видимость ${weather.visibility} м")
            }
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("⬆", Modifier.rotate(-weather.wind.deg.toFloat()), fontSize = 42.sp)
                Text("💨 ветер ${weather.wind.speed} м/с ")
            }
        }
    }
}