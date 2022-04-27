package ru.lrmk.composeapi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Price

@Composable
fun Lesson02(cities: List<String>) {
    // cities - список названий городов Ставропольского края. Состоит из строк.

    Column {
        cities.forEach {
            Text(it, Modifier.padding(5.dp))
            Divider()
        }
    }
}