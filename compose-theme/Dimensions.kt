package com.example.ember.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Ember — component dimension tokens.
 * Derived from the approved component library spec (Ember — Component Library.dc.html).
 * Use these constants in your :core:designsystem wrappers instead of raw literals.
 *
 * Usage:
 *   Modifier.height(EmberDimens.Button.height)
 *   Modifier.size(EmberDimens.Avatar.lg)
 */
object EmberDimens {

    // ─── Button ──────────────────────────────────────────────────────────────
    object Button {
        val height = 40.dp          // standard filled / tonal / outlined / text
        val heightSmall = 32.dp     // small variant
        val cornerRadius = 20.dp    // pill (full)
        val cornerRadiusSmall = 16.dp
        val horizontalPadding = 24.dp
        val horizontalPaddingSmall = 16.dp
        val iconSpacing = 8.dp      // gap between leading icon and label
        val minTouchTarget = 48.dp  // enforced by Modifier.minimumInteractiveComponentSize()
    }

    // ─── Icon button ─────────────────────────────────────────────────────────
    object IconButton {
        val touchTarget = 48.dp     // minimum interactive area
        val iconSize = 24.dp
        val filledTonalSize = 40.dp // filled-tonal square (e.g. bookmark)
        val filledTonalCorner = 12.dp // lg shape for square filled-tonal
        val filledTonalCornerCircle = 20.dp // when circle variant used
    }

    // ─── Text field ──────────────────────────────────────────────────────────
    object TextField {
        val cornerRadius = 4.dp         // outlined + filled
        val verticalPadding = 15.dp     // idle state inner vertical padding
        val verticalPaddingFocused = 14.dp // focused (border thickens by 1dp)
        val horizontalPadding = 14.dp
        val borderWidth = 1.dp
        val borderWidthFocused = 2.dp
        val labelOffset = (-9).dp       // notch label translateY from top edge
        val trailingIconSize = 20.dp    // error / clear icon
    }

    // ─── Card ────────────────────────────────────────────────────────────────
    object Card {
        val cornerRadius = 12.dp        // md shape
        val padding = 16.dp
        val shadowElevation = 1.dp      // resting (elevated card)
    }

    // ─── Avatar ──────────────────────────────────────────────────────────────
    object Avatar {
        val sm = 32.dp
        val md = 40.dp
        val lg = 56.dp
        val xl = 84.dp
        val ringWidth = 4.dp            // profile header ring border
        val postCardSize = 44.dp        // avatar in PostCard / feed rows
        val replyRowSize = 36.dp        // reply composer avatar
        val notificationRowSize = 32.dp // notification row mini avatar
    }

    // ─── Chip ────────────────────────────────────────────────────────────────
    object Chip {
        val height = 32.dp
        val cornerRadius = 8.dp         // sm shape
        val horizontalPadding = 12.dp
        val iconSize = 16.dp
        val iconSpacing = 6.dp
        /** Compact info chip (e.g. "Follows you") */
        val infoHorizontalPadding = 8.dp
    }

    // ─── Segmented button ────────────────────────────────────────────────────
    object SegmentedButton {
        val height = 40.dp
        val cornerRadius = 20.dp        // pill (full)
        val checkIconSize = 17.dp
        val checkIconSpacing = 6.dp
    }

    // ─── Tabs ────────────────────────────────────────────────────────────────
    object Tabs {
        val rowHeight = 46.dp
        val indicatorHeight = 3.dp
        val indicatorCorner = 3.dp      // 3dp top corners, 0 bottom
    }

    // ─── Top app bar ─────────────────────────────────────────────────────────
    object TopAppBar {
        val height = 64.dp              // small (M3 TopAppBar default = 64)
        val logoSize = 30.dp            // Ember mark inside bar
        val logoCorner = 9.dp
        val avatarSize = 36.dp          // action avatar
        val navIconPadding = 8.dp       // padding applied to nav icon touch area
        val titleFontSize = 22          // titleLarge sp — reference only; use M3 type token
    }

    // ─── Bottom navigation bar ───────────────────────────────────────────────
    object BottomNav {
        val height = 80.dp
        val indicatorWidth = 64.dp
        val indicatorHeight = 32.dp
        val indicatorCorner = 16.dp     // lg shape
        val iconSize = 24.dp
        val labelFontSize = 12          // labelMedium sp
        val topPadding = 13.dp          // item internal top padding
        val badgeSize = 8.dp            // dot badge diameter
        val badgeBorderWidth = 1.5.dp   // border that separates badge from surface
    }

    // ─── FAB ─────────────────────────────────────────────────────────────────
    object Fab {
        val size = 56.dp
        val cornerRadius = 16.dp        // lg shape
        val iconSize = 24.dp
        val extendedHeight = 56.dp
        val extendedHorizontalPadding = 20.dp
        val extendedIconSpacing = 10.dp
        val shadowElevation = 6.dp
    }

    // ─── Dialog ──────────────────────────────────────────────────────────────
    object Dialog {
        val cornerRadius = 28.dp        // xl shape
        val padding = 24.dp
        val iconSize = 44.dp            // icon container
        val iconContainerSize = 44.dp
        val actionButtonSpacing = 12.dp
    }

    // ─── Bottom sheet ────────────────────────────────────────────────────────
    object BottomSheet {
        val topCornerRadius = 28.dp     // xl shape (top only)
        val dragHandleWidth = 32.dp
        val dragHandleHeight = 4.dp
        val dragHandleCorner = 2.dp
        val dragHandleTopPadding = 16.dp
        val rowHeight = 56.dp
        val rowHorizontalPadding = 22.dp
        val rowIconSize = 22.dp
        val rowIconSpacing = 16.dp
        val dividerVerticalPadding = 4.dp
    }

    // ─── Snackbar ────────────────────────────────────────────────────────────
    object Snackbar {
        val height = 52.dp
        val cornerRadius = 4.dp         // xs shape
        val horizontalPadding = 16.dp
        val iconSize = 20.dp
        val iconSpacing = 12.dp
        val actionSpacingStart = 16.dp
        val bottomMargin = 16.dp        // distance from screen bottom edge
        val sideMargin = 16.dp
    }

    // ─── PostCard ────────────────────────────────────────────────────────────
    object PostCard {
        val horizontalPadding = 16.dp
        val verticalPadding = 14.dp
        val avatarToContentGap = 12.dp
        val bodyTopMargin = 2.dp
        val imageTopMargin = 10.dp
        val imageCorner = 16.dp
        val actionsTopMargin = 12.dp
        val actionIconSize = 19.dp
        val actionIconToLabelGap = 6.dp
        /** Max lines for feed (truncated); 0 = unlimited in detail view */
        val bodyMaxLinesFeed = 6
    }

    // ─── Compose post ────────────────────────────────────────────────────────
    object ComposePost {
        val charLimit = 280
        val bioCharLimit = 160
        val imageGridGap = 6.dp
        val imageGridCorner = 12.dp
        val imageRemoveButtonSize = 22.dp
        val progressRingSize = 22.dp
        val progressRingStroke = 2.5.dp
        val toolbarHeight = 56.dp
    }

    // ─── Image grid (1–4 images in a post) ───────────────────────────────────
    object ImageGrid {
        val singleHeight = 164.dp       // 16:9 single image
        val dualHeight = 150.dp         // 2 images side by side
        val quadHeight = 100.dp         // 2×2 each cell
        val gridGap = 6.dp
        val cornerRadius = 16.dp
    }

    // ─── User row (followers/following list, search results) ─────────────────
    object UserRow {
        val height = 72.dp              // 48dp avatar + 12dp vertical padding ×2
        val verticalPadding = 12.dp
        val horizontalPadding = 16.dp
        val avatarSize = 48.dp
        val avatarToContentGap = 12.dp
    }

    // ─── State composables ───────────────────────────────────────────────────
    object StateView {
        val iconContainerSize = 60.dp
        val iconSize = 30.dp
        val titleTopMargin = 12.dp
        val bodyTopMargin = 6.dp
        val ctaTopMargin = 12.dp
        val spinnerSm = 24.dp
        val spinnerMd = 36.dp
        val spinnerLg = 48.dp
        val spinnerStroke = 3.dp
    }
}
