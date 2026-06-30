package com.template.core.designsystem.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.template.core.designsystem.component.AppAvatar
import com.template.core.designsystem.component.AppButton
import com.template.core.designsystem.component.AppCard
import com.template.core.designsystem.component.AppChip
import com.template.core.designsystem.component.AppChipStyle
import com.template.core.designsystem.component.AppEmptyState
import com.template.core.designsystem.component.AppErrorState
import com.template.core.designsystem.component.AppIconButton
import com.template.core.designsystem.component.AppLoadingState
import com.template.core.designsystem.component.AppSegmentedButtons
import com.template.core.designsystem.component.AppTextField
import com.template.core.designsystem.theme.AppTheme
import com.template.core.designsystem.theme.EmberSpacing

@AppComponentPreviews
@Composable
internal fun AppButtonPreview(
    @PreviewParameter(AppButtonPreviewParameterProvider::class) state: AppButtonPreviewState
) {
    AppTheme {
        AppButton(onClick = {}, enabled = state.enabled, style = state.style) { Text(state.label) }
    }
}

@AppComponentPreviews
@Composable
internal fun AppInputsPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(EmberSpacing.md),
            verticalArrangement = Arrangement.spacedBy(EmberSpacing.sm),
        ) {
            AppTextField(
                value = "Value",
                onValueChange = {},
                label = "Label",
                placeholder = "Placeholder",
                modifier = Modifier.fillMaxWidth(),
            )
            Row(horizontalArrangement = Arrangement.spacedBy(EmberSpacing.sm)) {
                AppChip(label = "Assist", onClick = {})
                AppChip(
                    label = "Selected",
                    onClick = {},
                    selected = true,
                    style = AppChipStyle.Filter,
                )
            }
        }
    }
}

@AppComponentPreviews
@Composable
internal fun AppCardPreview(
    @PreviewParameter(AppCardPreviewParameterProvider::class) state: AppCardPreviewState
) {
    AppTheme {
        AppCard(style = state.style, modifier = Modifier.padding(EmberSpacing.md)) {
            Column(
                modifier = Modifier.padding(EmberSpacing.md),
                verticalArrangement = Arrangement.spacedBy(EmberSpacing.xs),
            ) {
                Text(text = state.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Reusable content slot", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@AppComponentPreviews
@Composable
internal fun AppAvatarAndIconPreview() {
    AppTheme {
        Row(
            modifier = Modifier.padding(EmberSpacing.md),
            horizontalArrangement = Arrangement.spacedBy(EmberSpacing.md),
        ) {
            AppAvatar(label = "Sample User")
            AppIconButton(onClick = {}, contentDescription = "Open options") {
                Box(modifier = Modifier.size(24.dp))
            }
        }
    }
}

@AppComponentPreviews
@Composable
internal fun AppSelectionPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(EmberSpacing.md),
            verticalArrangement = Arrangement.spacedBy(EmberSpacing.md),
        ) {
            AppSegmentedButtons(
                options = listOf("One", "Two", "Three"),
                selectedIndex = 0,
                onOptionSelected = {},
            )
        }
    }
}

@AppComponentPreviews
@Composable
internal fun AppStatePreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(EmberSpacing.md),
            verticalArrangement = Arrangement.spacedBy(EmberSpacing.md),
        ) {
            AppLoadingState(label = "Loading")
            AppEmptyState(title = "Nothing here", message = "Try changing the filters.")
            AppErrorState(title = "Could not load", message = "Check your connection.")
        }
    }
}
