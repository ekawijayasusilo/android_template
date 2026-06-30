package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.template.core.designsystem.theme.EmberDimens

/**
 * Thin M3 filter chip wrapper (FR-DS-2). Toggles via [selected]; selection state is exposed to a11y
 * by M3's FilterChip semantics (role + selected). Leading icon is decorative (label carries
 * meaning).
 */
@Composable
fun AppChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        label = { Text(label) },
        leadingIcon =
            leadingIcon?.let {
                {
                    Icon(
                        it,
                        contentDescription = null,
                        modifier = Modifier.size(EmberDimens.Chip.iconSize),
                    )
                }
            },
    )
}

@ThemePreviews
@Composable
internal fun AppChipPreview() {
    EmberPreview {
        Row(horizontalArrangement = Arrangement.spacedBy(EmberDimens.Chip.iconSpacing)) {
            AppChip(label = "All", selected = true, onClick = {})
            AppChip(label = "Unread", selected = false, onClick = {})
        }
    }
}
