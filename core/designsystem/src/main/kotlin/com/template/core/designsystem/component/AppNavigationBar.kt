package com.template.core.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Thin M3 bottom navigation bar wrapper (FR-DS-2). Each item's selected state + label are exposed
 * to a11y by M3's NavigationBarItem. The adaptive `NavigationSuiteScaffold` switch lives in
 * `:core:ui`.
 */
@Composable
fun AppNavigationBar(items: List<AppNavItem>, modifier: Modifier = Modifier) {
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = item.onClick,
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) },
            )
        }
    }
}

@ThemePreviews
@Composable
internal fun AppNavigationBarPreview() {
    EmberPreview {
        AppNavigationBar(
            items =
                listOf(
                    AppNavItem("Home", Icons.Filled.Home, selected = true, onClick = {}),
                    AppNavItem("Search", Icons.Filled.Search, selected = false, onClick = {}),
                )
        )
    }
}
