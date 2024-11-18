package com.example.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.style.StyleSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.ui.theme.NotesTheme

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
fun ScaffoldCreate(){

    val notes = remember { mutableStateListOf<String>() }
    val note = remember { mutableStateOf("") }

    Scaffold(
        topBar = {TopBar()},
        bottomBar = {BottomBar()},

        floatingActionButton = {
           CreateFAB(note, notes)
        },

    )
    { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            MyContent(note,notes)
        }
    }

}

@Composable
fun CreateFAB(note: MutableState<String>,notes: MutableList<String>){
    FloatingActionButton(onClick = {
        notes.add(note.value)
        note.value = ""
    }) {
        Icon(Icons.Filled.Add, contentDescription = "AddNote")
    }
}

@Composable
fun MyContent(note: MutableState<String>,notes: MutableList<String>){
    Column(modifier =
    Modifier
        .fillMaxSize()
        .padding( start = 10.dp, end = 10.dp)
        .background(Color(red = 198, green = 247, blue = 223)))
    {
        Column(){

            OutlinedTextField(value = note.value,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                onValueChange = { newText -> note.value = newText})
        }

        Spacer(Modifier.padding(8.dp))

        LazyColumn (modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)) {
            itemsIndexed(notes){
                    index, note ->
                ItemListRowNote(note,index,notes)
                Spacer(modifier = Modifier.padding(8.dp))

            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar (
        colors = topAppBarColors(
            containerColor = Color(red = 0, green = 135, blue = 70),
            titleContentColor = Color.White
        ),
        title = {
            Text("Заметки")
        }
    )
}

@Composable
fun BottomBar(){
    BottomAppBar(
        containerColor = Color(red = 0, green = 135, blue = 70),
        contentColor = Color.White,
    ) {
        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "BottomAppBar",)

    }
}

@Composable
fun ItemListRowNote(note: String, index: Int, notes: MutableList<String>){
    Row(modifier = Modifier
        .background(Color.White, shape = RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .padding(8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = note, fontSize = 18.sp, fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(0.9f))
        IconButton( onClick = {notes.removeAt(index)}, modifier = Modifier.fillMaxWidth(0.5f)) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}
