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
    Box(Modifier.fillMaxSize()) {
        if (session > 0)
            Text("Успех!", Modifier.align(Alignment.Center), color = Color.Gray, fontSize = 18.sp)
        else Column(Modifier.align(Alignment.Center).width(IntrinsicSize.Min)){
            Text("АВТОРИЗАЦИЯ ПОЛЬЗОВАТЕЛЯ", color = Color.Gray, fontSize = 18.sp)
            Text(error, color = Color.Red)
            OutlinedTextField(name, { name = it }, label = { Text("Имя пользователя") })
            OutlinedTextField(pass, { pass = it }, label = { Text("Пароль") } ,
                visualTransformation = PasswordVisualTransformation())
            Button(onClick = { login(name, pass) }, Modifier.fillMaxWidth().clip(CircleShape)) {
                Text("Вход в систему")
            }
        }
    }
}

