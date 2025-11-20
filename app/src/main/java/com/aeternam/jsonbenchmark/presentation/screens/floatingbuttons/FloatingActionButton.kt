package com.aeternam.jsonbenchmark.presentation.screens.floatingbuttons

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomFloatingButton(icon: ImageVector, description: String = "", onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick, containerColor = MaterialTheme.colorScheme.primary)
    {
        Icon(
            imageVector = icon,
            contentDescription = description
        )
    }
}