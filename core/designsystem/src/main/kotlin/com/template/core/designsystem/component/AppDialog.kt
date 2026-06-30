package com.template.core.designsystem.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Thin M3 alert dialog wrapper (FR-DS-2). Confirm/dismiss actions use [AppButton] so they inherit
 * Ember tokens + 48dp targets. [icon] is decorative (the title carries the meaning).
 */
@Composable
fun AppDialog(
    title: String,
    text: String,
    confirmLabel: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    dismissLabel: String? = null,
    icon: ImageVector? = null,
    destructive: Boolean = false,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        icon = icon?.let { { Icon(it, contentDescription = null) } },
        title = { Text(title) },
        text = { Text(text) },
        confirmButton = {
            AppButton(
                text = confirmLabel,
                onClick = onConfirm,
                style = if (destructive) AppButtonStyle.Destructive else AppButtonStyle.Text,
            )
        },
        dismissButton =
            dismissLabel?.let {
                { AppButton(text = it, onClick = onDismiss, style = AppButtonStyle.Text) }
            },
    )
}

@ThemePreviews
@Composable
internal fun AppDialogPreview() {
    EmberPreview {
        AppDialog(
            title = "Delete account?",
            text = "This permanently removes your data and cannot be undone.",
            confirmLabel = "Delete",
            onConfirm = {},
            onDismiss = {},
            dismissLabel = "Cancel",
            destructive = true,
        )
    }
}
