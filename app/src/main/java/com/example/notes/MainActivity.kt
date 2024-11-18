package com.example.notes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Column {
                ScaffoldCreate()
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCreate() {

    val notes = rememberSaveable() { mutableStateListOf<String>() }
    val note = rememberSaveable() { mutableStateOf("") }
    var selectedItem = rememberSaveable() { mutableStateOf("") }

    val context = LocalContext.current


    Scaffold(
        topBar = { TopBar(selectedItem.value, context) },
        bottomBar = { BottomBar(selectedItem.value, context) },

        floatingActionButton = {
            CreateFAB(note, notes)
        },

        )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MyContent(note, notes) { newValue -> selectedItem.value = newValue }
        }
    }

}

@Composable
fun CreateFAB(note: MutableState<String>, notes: MutableList<String>) {
    FloatingActionButton(onClick = {
        notes.add(note.value)
        note.value = ""
    }) {
        Icon(Icons.Filled.Add, contentDescription = "AddNote")
    }
}

@Composable
fun MyContent(
    note: MutableState<String>,
    notes: MutableList<String>,
    updateField: (String) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
            .background(Color(red = 198, green = 247, blue = 223, alpha = 255))
    )
    {
        Column() {

            OutlinedTextField(value = note.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onValueChange = { newText -> note.value = newText })
        }

        Spacer(Modifier.padding(8.dp))

        LazyColumn(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
        ) {
            itemsIndexed(notes) { index, note ->

                Row(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {

                            Toast
                                .makeText(
                                    context, "Выбрана заметка ${note}",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                            val newValue = note
                            updateField(newValue)
                        }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = note, fontSize = 18.sp, fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .fillMaxWidth(0.9f)
                    )
                    IconButton(
                        onClick = { notes.removeAt(index) },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(note: String, context: Context) {


    TopAppBar(
        title = { Text(text = "Заметки", fontSize = 22.sp) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Menu, contentDescription = "Меню")
            }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Звонок Совершен контакту ${note}", Toast.LENGTH_SHORT)
                    .show()
                Log.d("@@@", "Звонок Совершен контакту ${note}")
            }
            ) { Icon(Icons.Filled.Call, contentDescription = "Звонок") }

            IconButton(onClick = {
                (context as? Activity)?.finishAffinity()

            }) { Icon(Icons.Filled.Close, contentDescription = "Закрыть") }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(red = 0, green = 134, blue = 69),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.LightGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(note: String, context: Context) {
    BottomAppBar(
        actions = {
            IconButton(onClick = {
                Toast.makeText(
                    context, "Сообщение отправлено контакту ${note}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("@@@", "Сообщение отправлено контакту ${note}")
            }) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Отправить сообщение"
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = {
                Toast.makeText(
                    context, "Отредактирована заметка ${note}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("@@@", "Отредактирована заметка ${note}")
            })
            { Icon(Icons.Filled.Edit, contentDescription = "Правка") }
        },
        containerColor = Color(red = 0, green = 134, blue = 69),
        contentColor = Color.White
    )
}


