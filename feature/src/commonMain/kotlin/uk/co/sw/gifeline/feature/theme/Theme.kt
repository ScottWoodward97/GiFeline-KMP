package uk.co.sw.gifeline.feature.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
expect fun ToolbarTheming(
    colorScheme: androidx.compose.material3.ColorScheme,
    darkTheme: Boolean
)

@Composable
fun GiFelineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> ColorScheme.DarkColorScheme
        else -> ColorScheme.LightColorScheme
    }

    ToolbarTheming(colorScheme, darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = giFelineTypography(),
        content = content
    )
}