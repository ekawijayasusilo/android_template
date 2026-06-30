package com.template.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Ember — 4dp spacing grid. Use for padding and `Arrangement.spacedBy()`. Access via
 * `LocalEmberSpacing.current`; provided by [EmberTheme]. Defaults are also available as
 * [EmberSpacing] constants for previews/tests that render outside the theme.
 */
@Immutable
data class EmberSpacingTokens(
    val xxs: Dp = 4.dp, // hairline gaps, tight icon rows
    val xs: Dp = 8.dp, // icon ↔ label, small sibling elements
    val sm: Dp = 12.dp, // inside chips, card internal spacing
    val screen: Dp = 16.dp, // screen-edge horizontal padding
    val lg: Dp = 24.dp, // section gaps
    val xl: Dp = 32.dp, // major layout blocks
)

val EmberSpacing = EmberSpacingTokens()

val LocalEmberSpacing = staticCompositionLocalOf { EmberSpacing }
