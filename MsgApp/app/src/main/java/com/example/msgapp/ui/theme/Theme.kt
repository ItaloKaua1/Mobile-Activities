package com.example.msgapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF1E88E5),       // Azul médio
    onPrimary = Color.White,           // Branco para contraste com azul
    secondary = Color(0xFF43A047),     // Verde médio
    onSecondary = Color.White,         // Branco para contraste com verde
    surface = Color(0xFFFFFFFF),       // Branco puro para superfícies
    onSurface = Color(0xFF000000),     // Preto para texto em superfícies claras
    background = Color(0xFFF3F4F6),    // Cinza claro para fundo
    onBackground = Color(0xFF1B1B1B)   // Preto suave para texto em fundo claro
)

@Composable
fun MsgAppTheme(
    darkTheme: Boolean = false, // Você pode implementar controle de tema dinâmico
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
