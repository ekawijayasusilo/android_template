package com.template.core.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics

/**
 * Thin M3 top app bar wrapper (FR-DS-2). The title is marked as a [heading] for a11y; an optional
 * back affordance is exposed as a labelled [AppIconButton]. Actions are caller-supplied.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateBack: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(title, modifier = Modifier.semantics { heading() }) },
        navigationIcon = {
            if (onNavigateBack != null) {
                AppIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate up",
                    onClick = onNavigateBack,
                )
            }
        },
        actions = { actions() },
    )
}

@ThemePreviews
@Composable
internal fun AppTopBarPreview() {
    EmberPreview { AppTopBar(title = "Home", onNavigateBack = {}) }
}
