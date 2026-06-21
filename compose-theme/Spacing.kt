package com.example.ember.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Ember — 4dp spacing grid. Use for padding and Arrangement.spacedBy().
 * e.g. Modifier.padding(EmberSpacing.screen) ; Arrangement.spacedBy(EmberSpacing.sm)
 *
 * See also: EmberDimens (Dimensions.kt) for component-specific height/size tokens.
 */
object EmberSpacing {
    val xxs = 4.dp     // hairline gaps, tight icon rows
    val xs = 8.dp      // icon ↔ label, between small sibling elements
    val sm = 12.dp     // inside chips, card internal spacing
    val screen = 16.dp // screen-edge horizontal padding (all screens)
    val lg = 24.dp     // section gaps, between major content groups
    val xl = 32.dp     // major layout blocks, splash logo gap
}
