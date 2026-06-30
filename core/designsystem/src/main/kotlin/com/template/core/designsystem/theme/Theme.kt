package com.template.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            darkTheme -> EmberDarkColorScheme
            else -> EmberLightColorScheme
        }
    val extendedColors = if (darkTheme) EmberDarkExtendedColors else EmberLightExtendedColors

    CompositionLocalProvider(LocalEmberExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = EmberTypography,
            shapes = EmberShapes,
            content = content,
        )
    }
}

val MaterialTheme.emberExtendedColors: EmberExtendedColors
    @Composable get() = LocalEmberExtendedColors.current
