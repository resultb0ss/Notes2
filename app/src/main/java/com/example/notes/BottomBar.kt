package com.example.notes

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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