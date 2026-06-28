package com.template.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Brand colors with no Material 3 [androidx.compose.material3.ColorScheme] role (FR-DS-1). Material
 * You dynamic color only replaces the M3 scheme — these extended roles stay constant across themes,
 * so they live in their own [LocalEmberExtendedColors] CompositionLocal rather than the
 * ColorScheme.
 *
 * NOTE: `like`/`verified`/`brandGradient*` are sensible defaults pending exact values from
 * docs/design/Ember*.dc.html — confirm with design before relying on them downstream.
 */
@Immutable
data class EmberExtendedColors(
    val like: Color,
    val onLike: Color,
    val verified: Color,
    val brandGradientStart: Color,
    val brandGradientEnd: Color,
)

internal val EmberExtendedColorsLight =
    EmberExtendedColors(
        like = Color(0xFFE0245E),
        onLike = Color(0xFFFFFFFF),
        verified = Color(0xFF1D9BF0),
        brandGradientStart = Color(0xFFBB3E1C),
        brandGradientEnd = Color(0xFFD9772F),
    )

internal val EmberExtendedColorsDark =
    EmberExtendedColors(
        like = Color(0xFFFF6090),
        onLike = Color(0xFF3A0014),
        verified = Color(0xFF6CC4FF),
        brandGradientStart = Color(0xFFFFB59E),
        brandGradientEnd = Color(0xFFE7BDB0),
    )

/** Access via `LocalEmberExtendedColors.current`; provided by [EmberTheme]. */
val LocalEmberExtendedColors = staticCompositionLocalOf { EmberExtendedColorsLight }
