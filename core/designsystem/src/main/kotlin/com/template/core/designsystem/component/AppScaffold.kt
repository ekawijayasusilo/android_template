package com.template.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Thin M3 scaffold wrapper (FR-DS-2): the single root for screens. Bundles a [SnackbarHostState]
 * and wires top bar / bottom bar / FAB slots. Edge-to-edge inset handling is applied by callers
 * using the provided [PaddingValues] (FR-ADPT-3).
 */
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = content,
    )
}

@ThemePreviews
@Composable
internal fun AppScaffoldPreview() {
    EmberPreview {
        AppScaffold(topBar = { AppTopBar(title = "Inbox") }) { padding ->
            EmptyState(
                title = "All caught up",
                description = "You have no new messages.",
                modifier = Modifier.padding(padding),
            )
        }
    }
}
