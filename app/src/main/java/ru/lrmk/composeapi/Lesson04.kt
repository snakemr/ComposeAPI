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
    // cities - список названий городов Российской Федерацц. Состоит из 1000+ строк.
    var name by remember { mutableStateOf("") }
    Column {
        OutlinedTextField(value = name, onValueChange = { name = it}, label = { 
            Text(text = "Поиск города...")
        })
        LazyColumn {
            items(cities.filter { name in it }) {
                Text(it, Modifier.padding(10.dp))
                Divider()
            }
        }
    }
}