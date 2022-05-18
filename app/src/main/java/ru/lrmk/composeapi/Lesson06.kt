package ru.lrmk.composeapi

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun Lesson06(session: Int, error: String, login: (name:String,pass:String)->Unit) {
    // session - идентификатор сессии.
    // если он равен 0, то входа ещё не было, и нужно отобразить форму авторизации
    // если он больше 0, то поздравить пользователя с успешной авторизацией
    // error - сообщение об ошибке. если эта строка не пуста, её надо отобразить над формой
    // login - функция, которую надо вызвать по нажатию кнопки "Вход в систему"

    var name by remember { mutableStateOf("")  }    // переменная для ввода имени пользователя
    var pass by remember { mutableStateOf("")  }    // переменная для ввода пароля

    Text("""Урок 6. Форма ввода пароля
        
        Всё будет внутри Box(Modifier.fillMaxSize()) {...} чтобы форма появлялась по центру экрана.
         
        Если session>0, то отобразить в Text сообщение "Успех", Modifier.align(Alignment.Center) - по центру,          
        иначе отобразить Column(...){...} c двумя модификаторами:
        Modifier.align(Alignment.Center).width(IntrinsicSize.Min) первый - по центру, второй - чтобы кнопка не растянулась на всю ширину.
        
        Внутри - серый Text заголовка формы, красный Text для строки error, два поля ввода и кнопка.
        
        Для перврго поля ввода OutlinedTextField параметры будут: name, {name=it} и label={Text("...")}
        для второго - аналогично с переменной pass, но добавить четвёртый, чтобы скрыть ввод символов: visualTransformation = PasswordVisualTransformation())
        
        Кнопка Button должна вызывать функцию login, передав в неё name и pass. 
        
        Дайте кнопке модификаторы, чтобы растянуть её на ширину формы и скруглить края:  
        Modifier.fillMaxWidth().clip(CircleShape)
        
        В блоке {...} элемента Button будет простой Text с надписью для кнопки.
        
        Для проверки: имя - aas, пароль - 123""".trimIndent())

}

