package com.example.notes

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyContent(
    note: MutableState<String>,
    notes: MutableList<String>,
    updateField: (String) -> Unit
) {

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

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
                        onClick = { openDialog.value = true },
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))
                getDialog(openDialog,context,notes,index )

            }
        }


    }
}