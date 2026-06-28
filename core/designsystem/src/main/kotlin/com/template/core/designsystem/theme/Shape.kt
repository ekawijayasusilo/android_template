package com.template.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Ember — Material 3 corner scale. `none` = M3 RectangleShape default; `full` (pill) = 50% via
 * [androidx.compose.foundation.shape.CircleShape] at the call site (e.g. buttons, chips, avatars).
 */
val EmberShapes =
    Shapes(
        extraSmall = RoundedCornerShape(4.dp),
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp),
        extraLarge = RoundedCornerShape(28.dp),
    )
