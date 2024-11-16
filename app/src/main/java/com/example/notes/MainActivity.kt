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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            myContent()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun myContent(){

    val notes = remember { mutableStateListOf<String>() }
    val note = remember { mutableStateOf("") }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(onClick = {
                notes.add(note.value)
                note.value = ""
            }) {
                Icon(Icons.Filled.Add, contentDescription = "AddNote")
            }
        },

        content = {
            Column(modifier =
            Modifier
                .fillMaxSize()
                .padding(top = 100.dp, start = 10.dp, end = 10.dp)
                .background(Color.LightGray))
            {
                Column(modifier = Modifier.border(color = Color.Black, width = 1.dp)){
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray),
                        horizontalArrangement = Arrangement.Center){
                        Text(text = "Заметки",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White, modifier = Modifier.padding(8.dp))
                    }
                    TextField(value = note.value,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { newText -> note.value = newText})
                }

                Spacer(Modifier.padding(8.dp))

                LazyColumn (modifier = Modifier
                    .background(color = Color.LightGray)
                    .padding(start = 8.dp, end = 8.dp)) {
                    itemsIndexed(notes){
                            index, note ->
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
                        Spacer(modifier = Modifier.padding(8.dp))

                    }
                }


            }
        }
    )

}