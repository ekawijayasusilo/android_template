package com.template.core.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Thin M3 icon-button wrapper (FR-DS-2). [contentDescription] is required (a11y: icon-only controls
 * must be labelled); M3 IconButton already provides the 48dp touch target.
 */
@Composable
fun AppIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

@ThemePreviews
@Composable
internal fun AppIconButtonPreview() {
    EmberPreview {
        AppIconButton(icon = Icons.Filled.Settings, contentDescription = "Settings", onClick = {})
    }
}
