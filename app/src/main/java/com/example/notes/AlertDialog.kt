package com.example.notes

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: @Composable () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {Icon(icon, contentDescription = "Icon")},
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText )},
        onDismissRequest = {onDismissRequest},
        confirmButton = {onConfirmation},
        dismissButton = {
            TextButton(onClick = {onDismissRequest()}) {
                Text(text = "Отмена")
            }
        },
        containerColor = MaterialTheme.colorScheme.onSecondary,
        titleContentColor = Color.Red,
        iconContentColor = Color.Black
    )
}