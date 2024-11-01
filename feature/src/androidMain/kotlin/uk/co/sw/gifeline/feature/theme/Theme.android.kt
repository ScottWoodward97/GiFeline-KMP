package uk.co.sw.gifeline.feature.theme

import androidx.compose.runtime.Composable
import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
actual fun ToolbarTheming(
    colorScheme: ColorScheme,
    darkTheme: Boolean
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
}