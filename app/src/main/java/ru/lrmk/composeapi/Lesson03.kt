package ru.lrmk.composeapi

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Price

@Composable
fun Lesson03(price: List<Price>) {
    LazyColumn {
        items(price) {
            Row(Modifier.padding(10.dp).height(42.dp)) {
                Text(it.name, Modifier.weight(1f))
                Text(it.cost.toString())
            }
            Divider()
        }
    }
}