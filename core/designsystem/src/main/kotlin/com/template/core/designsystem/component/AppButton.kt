package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.template.core.designsystem.theme.EmberDimens

/**
 * Thin M3 button wrapper (FR-DS-2). One seam for Ember tokens + a11y. M3 already enforces the 48dp
 * touch target via `minimumInteractiveComponentSize()`. Apps use this, never raw M3 buttons.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: AppButtonStyle = AppButtonStyle.Filled,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    val content: @Composable () -> Unit = {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null)
                Spacer(Modifier.width(EmberDimens.Button.iconSpacing))
            }
            Text(text)
        }
    }
    when (style) {
        AppButtonStyle.Filled ->
            Button(onClick = onClick, modifier = modifier, enabled = enabled) { content() }
        AppButtonStyle.Tonal ->
            FilledTonalButton(onClick = onClick, modifier = modifier, enabled = enabled) {
                content()
            }
        AppButtonStyle.Outlined ->
            OutlinedButton(onClick = onClick, modifier = modifier, enabled = enabled) { content() }
        AppButtonStyle.Text ->
            TextButton(onClick = onClick, modifier = modifier, enabled = enabled) { content() }
        AppButtonStyle.Destructive ->
            Button(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = destructiveColors(),
            ) {
                content()
            }
    }
}

@Composable
private fun destructiveColors(): ButtonColors =
    ButtonDefaults.buttonColors(
        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.error,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onError,
    )

@ThemePreviews
@Composable
internal fun AppButtonPreview() {
    EmberPreview {
        Row(horizontalArrangement = Arrangement.spacedBy(EmberDimens.Button.iconSpacing)) {
            AppButton(text = "Filled", onClick = {})
            AppButton(text = "Tonal", onClick = {}, style = AppButtonStyle.Tonal)
            AppButton(text = "Delete", onClick = {}, style = AppButtonStyle.Destructive)
        }
    }
}

@Preview
@Composable
internal fun AppButtonOutlinedPreview() {
    EmberPreview { AppButton(text = "Outlined", onClick = {}, style = AppButtonStyle.Outlined) }
}
