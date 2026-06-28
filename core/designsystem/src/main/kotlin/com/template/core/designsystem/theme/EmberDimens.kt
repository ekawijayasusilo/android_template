package com.template.core.designsystem.theme

import androidx.compose.ui.unit.dp

/**
 * Ember — generic component dimension tokens (Ember — Component Library.dc.html). Use these in the
 * `App*` wrappers instead of raw dp literals. Kept free of social nouns (SC-ISOLATION-2): only
 * generic, reusable atoms live here.
 */
object EmberDimens {

    object Button {
        val height = 40.dp
        val heightSmall = 32.dp
        val horizontalPadding = 24.dp
        val iconSpacing = 8.dp
    }

    object IconButton {
        val touchTarget = 48.dp
        val iconSize = 24.dp
    }

    object TextField {
        val trailingIconSize = 20.dp
    }

    object Card {
        val padding = 16.dp
        val shadowElevation = 1.dp
    }

    object Avatar {
        val sm = 32.dp
        val md = 40.dp
        val lg = 56.dp
        val xl = 84.dp
    }

    object Chip {
        val iconSize = 16.dp
        val iconSpacing = 6.dp
    }

    object TopAppBar {
        val height = 64.dp
    }

    object Dialog {
        val iconSize = 24.dp
    }

    object BottomSheet {
        val rowHeight = 56.dp
        val rowIconSpacing = 16.dp
    }

    object StateView {
        val iconContainerSize = 60.dp
        val iconSize = 30.dp
        val spinnerMd = 36.dp
        val contentMaxWidth = 320.dp
    }
}
