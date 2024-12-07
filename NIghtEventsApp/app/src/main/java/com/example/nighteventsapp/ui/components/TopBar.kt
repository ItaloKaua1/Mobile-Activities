package com.example.nighteventsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    onThemeToggle: () -> Unit,
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        title = {Text("EventApp")},
        navigationIcon = {
            IconButton(onClick = onOpenDrawer) {
                Icon(Icons.Default.BrightnessHigh, contentDescription = "Toggle Theme")
            }
        }
    )
}