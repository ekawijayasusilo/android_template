package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

/**
 * Generic circular avatar placeholder (FR-DS-2): renders initials on a tonal circle. Image loading
 * is a feature concern (Coil stays out of `:core:designsystem`). [contentDescription] is required
 * so the avatar is announced (a11y); pass the person's display name.
 */
@Composable
fun AppAvatar(
    initials: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: AppAvatarSize = AppAvatarSize.Medium,
) {
    Surface(
        modifier =
            modifier.size(size.dp).semantics { this.contentDescription = contentDescription },
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = initials.take(2).uppercase(), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@ThemePreviews
@Composable
internal fun AppAvatarPreview() {
    EmberPreview {
        AppAvatar(initials = "AL", contentDescription = "Ada Lovelace", size = AppAvatarSize.Large)
    }
}
