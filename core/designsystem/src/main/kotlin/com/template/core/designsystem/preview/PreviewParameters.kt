package com.template.core.designsystem.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.template.core.designsystem.component.AppButtonStyle
import com.template.core.designsystem.component.AppCardStyle

data class AppButtonPreviewState(
    val label: String,
    val style: AppButtonStyle,
    val enabled: Boolean = true,
)

class AppButtonPreviewParameterProvider : PreviewParameterProvider<AppButtonPreviewState> {
    override val values =
        sequenceOf(
            AppButtonPreviewState(label = "Filled", style = AppButtonStyle.Filled),
            AppButtonPreviewState(label = "Tonal", style = AppButtonStyle.Tonal),
            AppButtonPreviewState(label = "Outlined", style = AppButtonStyle.Outlined),
            AppButtonPreviewState(label = "Text", style = AppButtonStyle.Text),
            AppButtonPreviewState(label = "Error", style = AppButtonStyle.Destructive),
            AppButtonPreviewState(
                label = "Disabled",
                style = AppButtonStyle.Filled,
                enabled = false,
            ),
        )
}

data class AppCardPreviewState(val title: String, val style: AppCardStyle)

class AppCardPreviewParameterProvider : PreviewParameterProvider<AppCardPreviewState> {
    override val values =
        sequenceOf(
            AppCardPreviewState(title = "Filled card", style = AppCardStyle.Filled),
            AppCardPreviewState(title = "Elevated card", style = AppCardStyle.Elevated),
            AppCardPreviewState(title = "Outlined card", style = AppCardStyle.Outlined),
        )
}
