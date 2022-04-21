package ru.lrmk.composeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lrmk.composeapi.api.Api
import ru.lrmk.composeapi.api.DoLesson
import ru.lrmk.composeapi.api.Lessons
import ru.lrmk.composeapi.ui.theme.ComposeAPITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = "lesson"
        val api = Api()
        val prefs = getPreferences(MODE_PRIVATE)

        setContent {
            var lesson by rememberSaveable { mutableStateOf(
                Lessons.valueOf(prefs.getString(pref, null) ?: Lessons.L1.name)
            ) }
            var sample by rememberSaveable { mutableStateOf(false) }
            ComposeAPITheme {
                Box {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        if (sample) lesson.Sample(api) else DoLesson(lesson, api)
                    }
                    Box(Modifier.align(Alignment.TopEnd)) {
                        var expanded by remember { mutableStateOf(false) }
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.Menu, contentDescription = "")
                        }
                        DropdownMenu(expanded, { expanded = false }) {
                            DropdownMenuItem(onClick = { expanded = false; sample = !sample }) {
                                Switch(sample, { sample = !sample })
                                Spacer(Modifier.width(10.dp))
                                Text("Показать пример")
                            }
                            Divider()
                            Lessons.values().forEach {
                                DropdownMenuItem(onClick = {
                                    expanded = false
                                    lesson = it
                                    prefs.edit().putString(pref, it.name).apply()
                                }) {
                                    RadioButton(lesson == it, onClick = {})
                                    Spacer(Modifier.width(10.dp))
                                    Text(it.Title)
                                }
                            }
                        }
                    }
                    if (sample) Snackbar(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(10.dp),
                        backgroundColor = MaterialTheme.colors.error) {
                        Text("Это пример! Вам надо сделать так же!")
                    }
                }
            }
        }
    }
}
