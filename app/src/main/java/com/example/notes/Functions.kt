package com.example.notes

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource

@Composable
fun getDialog(
    openDialog: MutableState<Boolean>,
    context: Context,
    notes: MutableList<String>,
    index: Int
) {

    if (openDialog.value) {

        DialogWithImage(
            onDismissRequest = {openDialog.value = false
                 },
            onConfirmation = {
                openDialog.value = false
                notes.removeAt(index)
                Toast.makeText(context, "Элемент удален", Toast.LENGTH_SHORT).show()

            },
            painter = painterResource(id = R.drawable.trash),
            imageDescription = ""

        )
    }
}