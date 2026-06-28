package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.template.core.designsystem.theme.EmberDimens
import com.template.core.designsystem.theme.EmberSpacing

private const val LOADING_PREVIEW_PROGRESS = 0.7f

/**
 * Generic full-screen state composables (FR-DS-2): loading / empty / error. Features supply copy +
 * retry. The loading spinner is announced as a polite [liveRegion] for a11y.
 */
@Composable
fun LoadingState(modifier: Modifier = Modifier, contentDescription: String = "Loading") {
    Column(
        modifier = modifier.fillMaxSize().semantics { liveRegion = LiveRegionMode.Polite },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(EmberSpacing.xs, Alignment.CenterVertically),
    ) {
        // Indeterminate at runtime; a static determinate frame under inspection
        // (previews/screenshot
        // tests) so Roborazzi goldens are deterministic (the spinning arc would otherwise vary).
        if (LocalInspectionMode.current) {
            CircularProgressIndicator(
                progress = { LOADING_PREVIEW_PROGRESS },
                modifier = Modifier.size(EmberDimens.StateView.spinnerMd),
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.size(EmberDimens.StateView.spinnerMd))
        }
        Text(text = contentDescription, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun EmptyState(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Inbox,
    action: (@Composable () -> Unit)? = null,
) {
    MessageState(
        title = title,
        description = description,
        icon = icon,
        modifier = modifier,
        action = action,
    )
}

@Composable
fun ErrorState(
    title: String,
    description: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Inbox,
    retryLabel: String = "Retry",
) {
    MessageState(
        title = title,
        description = description,
        icon = icon,
        modifier = modifier,
        action = { AppButton(text = retryLabel, onClick = onRetry, style = AppButtonStyle.Tonal) },
    )
}

@Composable
private fun MessageState(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(EmberSpacing.screen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(EmberSpacing.xs, Alignment.CenterVertically),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(EmberDimens.StateView.iconSize),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = EmberDimens.StateView.contentMaxWidth),
        )
        if (action != null) {
            action()
        }
    }
}

@ThemePreviews
@Composable
internal fun EmptyStatePreview() {
    EmberPreview {
        EmptyState(
            title = "Nothing here yet",
            description = "When there's activity, it'll show up here.",
        )
    }
}

@ThemePreviews
@Composable
internal fun ErrorStatePreview() {
    EmberPreview {
        ErrorState(
            title = "Something went wrong",
            description = "Check your connection and try again.",
            onRetry = {},
        )
    }
}

@ThemePreviews
@Composable
internal fun LoadingStatePreview() {
    // Force inspection mode so the spinner renders its static (determinate) frame — keeps the
    // screenshot golden deterministic while still exercising LoadingState's layout + a11y
    // semantics.
    EmberPreview { CompositionLocalProvider(LocalInspectionMode provides true) { LoadingState() } }
}
