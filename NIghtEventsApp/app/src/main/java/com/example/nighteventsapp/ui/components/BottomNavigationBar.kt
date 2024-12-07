package com.example.nighteventsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController){
    NavigationBar {
        val items = listOf(
            Triple("home", "Home", Icons.Default.Home),
            Triple("events", "Inscritos", Icons.Default.Event),
            Triple("favorites", "Favoritos", Icons.Default.Favorite)
        )
        items.forEach{(route, label, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) },
                selected = false,
                onClick = {navController.navigate(route)}
            )
        }
    }
}