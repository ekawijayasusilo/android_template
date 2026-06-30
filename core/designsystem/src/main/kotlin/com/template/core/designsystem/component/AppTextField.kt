package com.template.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Thin M3 outlined text field wrapper (FR-DS-2). Label is always supplied (a11y: the field is
 * self-describing); error state surfaces via [isError] + [supportingText].
 */
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    supportingText: String? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        isError = isError,
        singleLine = singleLine,
        label = { Text(label) },
        supportingText = supportingText?.let { { Text(it) } },
    )
}

/** Drives the [AppTextField] preview across empty / filled / error states (FR-DS-3). */
internal class TextFieldStateProvider : PreviewParameterProvider<TextFieldState> {
    override val values =
        sequenceOf(
            TextFieldState(value = "", isError = false, supporting = null),
            TextFieldState(value = "ada@lovelace.dev", isError = false, supporting = null),
            TextFieldState(value = "nope", isError = true, supporting = "Enter a valid email"),
        )
}

internal data class TextFieldState(val value: String, val isError: Boolean, val supporting: String?)

@ThemePreviews
@Composable
internal fun AppTextFieldPreview(
    @PreviewParameter(TextFieldStateProvider::class) state: TextFieldState
) {
    EmberPreview {
        AppTextField(
            value = state.value,
            onValueChange = {},
            label = "Email",
            isError = state.isError,
            supportingText = state.supporting,
        )
    }
}
