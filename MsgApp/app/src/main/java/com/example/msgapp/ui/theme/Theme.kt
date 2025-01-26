package com.example.msgapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
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
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}