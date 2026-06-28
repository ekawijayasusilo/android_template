package com.template.core.designsystem.component

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.template.core.designsystem.theme.EmberTheme

/**
 * Multipreview (FR-DS-3): renders every annotated atom across light/dark and the standard
 * font-scale range (1.0×…2.0×, FR-A11Y-4). ComposablePreviewScanner expands each combination into
 * its own screenshot + a11y assertion.
 */
@PreviewLightDark @PreviewFontScale internal annotation class ThemePreviews

/**
 * Wraps preview content in [EmberTheme] + a themed surface so tokens resolve. Dynamic color off for
 * deterministic goldens.
 */
@Composable
internal fun EmberPreview(content: @Composable () -> Unit) {
    EmberTheme(dynamicColor = false) { Surface(content = content) }
}
