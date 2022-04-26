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
fun Lesson02(price: List<String>) {
    Column {
        price.forEach {
            Text(it, Modifier.padding(10.dp))
            Divider()
        }
    }
}