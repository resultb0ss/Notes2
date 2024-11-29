package com.example.notes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun CreateFAB(note: MutableState<String>, notes: MutableList<String>) {
    FloatingActionButton(onClick = {
        notes.add(note.value)
        note.value = ""
    }) {
        Icon(Icons.Filled.Add, contentDescription = "AddNote")
    }
}