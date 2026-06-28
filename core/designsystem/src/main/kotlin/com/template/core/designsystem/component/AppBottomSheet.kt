package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.template.core.designsystem.theme.EmberDimens

/**
 * Thin M3 modal bottom sheet wrapper (FR-DS-2). Caller controls visibility (only render when
 * shown). Content is laid out with Ember screen padding; sheet shape/drag-handle come from M3 +
 * Ember shapes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss, modifier = modifier, sheetState = sheetState) {
        Column(
            Modifier.fillMaxWidth().padding(horizontal = EmberDimens.BottomSheet.rowIconSpacing)
        ) {
            content()
        }
    }
}

// Preview renders the sheet's content layout directly; the modal scrim/popup itself is not
// representable in a static preview (FR-DS-3 still covers the content surface).
@ThemePreviews
@Composable
internal fun AppBottomSheetContentPreview() {
    EmberPreview {
        Column(Modifier.padding(EmberDimens.BottomSheet.rowIconSpacing)) {
            Text("Share")
            Text("Copy link")
            Text("Mute")
        }
    }
}
