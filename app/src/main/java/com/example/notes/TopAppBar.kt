package com.example.notes

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


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