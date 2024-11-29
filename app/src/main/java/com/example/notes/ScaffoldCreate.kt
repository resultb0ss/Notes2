package com.example.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCreate() {

    val notes = remember { mutableStateListOf<String>() }
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