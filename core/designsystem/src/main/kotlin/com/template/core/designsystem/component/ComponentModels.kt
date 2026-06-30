package com.template.core.designsystem.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.template.core.designsystem.theme.EmberDimens

/** Public value types shared by the `App*` atoms (FR-DS-2). */

/** Visual variants of [AppButton]. `Destructive` is a filled button tinted with the error role. */
enum class AppButtonStyle {
    Filled,
    Tonal,
    Outlined,
    Text,
    Destructive,
}

/** Avatar diameter tokens for [AppAvatar]. */
enum class AppAvatarSize(val dp: Dp) {
    Small(EmberDimens.Avatar.sm),
    Medium(EmberDimens.Avatar.md),
    Large(EmberDimens.Avatar.lg),
    ExtraLarge(EmberDimens.Avatar.xl),
}

/**
 * One destination in [AppNavigationBar]. [icon]'s meaning is carried by [label] (icon stays
 * decorative).
 */
@Immutable
data class AppNavItem(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean,
    val onClick: () -> Unit,
)
