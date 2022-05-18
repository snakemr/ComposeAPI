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
    // weather - объект, содержащий всю информацию о текущей погоде, в котором:
    // name - название города или местности, coord.lat и coord.lon - гео координаты,
    // sys.sunrise и sys.sunset - время восхода и заката,
    // main.temp - текущая температура, main.temp_min и main.temp_max - её минимум и максимум,
    // main.feels_like - температура "ощущается как",
    // main.humidity - влажность, main.pressure - давление, clouds.all - облачность,
    // rain.mm - осадки, visibility - видимость,
    // wind.speed и wind.deg - скорость и направление ветра.
    // weather.weather - список из одного или более элементов, в каждом из которых есть:
    // description - описание погоды на русском, icon - имя файла для значка погоды
    // информацию предоставляет сайт https://openweathermap.org/

    // Используйте функцию Api.time(...) для отображения времени восхода и заката
    // И функцию Api.icon(...) для загрузки с сервера нужного значка погоды (в элемент Image())

    // Для выбора первого элемента списка weather.weather используйте такой аккуратный способ:
    // weather.weather.firstOrNull()?.icon  - для значка погоды
    // weather.weather.firstOrNull()?.description ?: ""  - для описания

    // Для оформления можно использовать символы: ° ℃ 🌍 🌄 🌇 💧 📩  🌥  ☔  👓 💨 (или через Win+.)
    // и символ ⬆ для направления ветра, повёрнутый на нужный угол при помощи модификатора
    // Modifier.rotate(-weather.wind.deg.toFloat()), и лучше крупным шрифтом: fontSize = 42.sp

    Text("""Урок 5. Погодный информер
        
        В объекте weather дана информация о текущей погоде в нашем городе.  
        
        Используйте нужное количество текстовых элементов Text(...) и один Image(...) для отображения всех данных.
         
        Самостоятельно группируйте их при необходимости внутри элементов Row{...}, а их - внутри одного Column{...}
         
        Общий внешний вид - на ваше усмотрение, но все данные должны быть отображены в понятном виде.
        
        Рекомендуется использовать шаблоны: 
        Text("${weather.main.temp} ℃ ", fontSize = 42.sp)
        
        Основному Column укажите в круглых скобках фоновый градиент цвета (цвет - на ваш вкус): 
        Modifier.background( Brush.linearGradient(listOf(Color.Cyan, Color.Blue)) )
        
        Остальные технические подробности смотрите в комметарии к функции урока.""".trimIndent())

}