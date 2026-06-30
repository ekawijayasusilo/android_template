package com.template.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.template.core.designsystem.theme.EmberDimens
import com.template.core.designsystem.theme.EmberSpacing
import com.template.core.designsystem.theme.emberExtendedColors

enum class AppButtonStyle {
    Filled,
    Tonal,
    Outlined,
    Text,
    Destructive,
}

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: AppButtonStyle = AppButtonStyle.Filled,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val minTargetModifier =
        modifier.defaultMinSize(
            minWidth = EmberDimens.Button.minTouchTarget,
            minHeight = EmberDimens.Button.minTouchTarget,
        )
    when (style) {
        AppButtonStyle.Filled ->
            Button(
                onClick = onClick,
                modifier = minTargetModifier,
                enabled = enabled,
                contentPadding = contentPadding,
                content = content,
            )
        AppButtonStyle.Tonal ->
            androidx.compose.material3.FilledTonalButton(
                onClick = onClick,
                modifier = minTargetModifier,
                enabled = enabled,
                contentPadding = contentPadding,
                content = content,
            )
        AppButtonStyle.Outlined ->
            OutlinedButton(
                onClick = onClick,
                modifier = minTargetModifier,
                enabled = enabled,
                contentPadding = contentPadding,
                content = content,
            )
        AppButtonStyle.Text ->
            TextButton(
                onClick = onClick,
                modifier = minTargetModifier,
                enabled = enabled,
                contentPadding = contentPadding,
                content = content,
            )
        AppButtonStyle.Destructive ->
            Button(
                onClick = onClick,
                modifier = minTargetModifier,
                enabled = enabled,
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                    ),
                contentPadding = contentPadding,
                content = content,
            )
    }
}

@Composable
fun AppIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier =
            modifier
                .defaultMinSize(
                    minWidth = EmberDimens.IconButton.touchTarget,
                    minHeight = EmberDimens.IconButton.touchTarget,
                )
                .semantics { this.contentDescription = contentDescription },
        enabled = enabled,
        content = content,
    )
}

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.defaultMinSize(minHeight = EmberDimens.TextField.minHeight),
        enabled = enabled,
        isError = isError,
        singleLine = singleLine,
        label = label?.let { { Text(it) } },
        placeholder = placeholder?.let { { Text(it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
    )
}

enum class AppCardStyle {
    Filled,
    Elevated,
    Outlined,
}

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    style: AppCardStyle = AppCardStyle.Filled,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = MaterialTheme.shapes.medium
    when (style) {
        AppCardStyle.Filled ->
            if (onClick == null) {
                Card(modifier = modifier, shape = shape, content = content)
            } else {
                Card(onClick = onClick, modifier = modifier, shape = shape, content = content)
            }
        AppCardStyle.Elevated ->
            if (onClick == null) {
                ElevatedCard(
                    modifier = modifier,
                    shape = shape,
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
                    content = content,
                )
            } else {
                ElevatedCard(
                    onClick = onClick,
                    modifier = modifier,
                    shape = shape,
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
                    content = content,
                )
            }
        AppCardStyle.Outlined ->
            if (onClick == null) {
                OutlinedCard(modifier = modifier, shape = shape, content = content)
            } else {
                OutlinedCard(
                    onClick = onClick,
                    modifier = modifier,
                    shape = shape,
                    content = content,
                )
            }
    }
}

@Composable
fun AppAvatar(
    label: String,
    modifier: Modifier = Modifier,
    size: Dp = EmberDimens.Avatar.medium,
    image: Painter? = null,
    contentDescription: String? = null,
) {
    Surface(
        modifier = modifier.size(size),
        shape = CircleShape,
        color = MaterialTheme.emberExtendedColors.avatarFallback,
        contentColor = MaterialTheme.emberExtendedColors.onAvatarFallback,
    ) {
        if (image == null) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = initialsFor(label),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Image(
                painter = image,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

enum class AppChipStyle {
    Assist,
    Filter,
}

@Composable
fun AppChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    style: AppChipStyle = AppChipStyle.Assist,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    when (style) {
        AppChipStyle.Assist ->
            AssistChip(
                onClick = onClick,
                label = { Text(label) },
                modifier = modifier.defaultMinSize(minHeight = 48.dp),
                enabled = enabled,
                leadingIcon = leadingIcon,
            )
        AppChipStyle.Filter ->
            FilterChip(
                selected = selected,
                onClick = onClick,
                label = { Text(label) },
                modifier = modifier.defaultMinSize(minHeight = 48.dp),
                enabled = enabled,
                leadingIcon = leadingIcon,
            )
    }
}

@Composable
fun AppSegmentedButtons(
    options: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                selected = index == selectedIndex,
                onClick = { onOptionSelected(index) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                enabled = enabled,
                modifier = Modifier.defaultMinSize(minHeight = 48.dp),
            ) {
                Text(option)
            }
        }
    }
}

@Composable
fun AppTabs(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryTabRow(selectedTabIndex = selectedIndex, modifier = modifier) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = index == selectedIndex,
                onClick = { onTabSelected(index) },
                text = { Text(title) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
            ),
    )
}

data class AppNavigationItem(
    val label: String,
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
)

@Composable
fun AppNavigationBar(items: List<AppNavigationItem>, modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier.defaultMinSize(minHeight = EmberDimens.NavigationBar.height)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.selected,
                onClick = item.onClick,
                icon = item.icon,
                label = { Text(item.label) },
            )
        }
    }
}

@Composable
fun AppDialog(
    title: String,
    message: String,
    confirmLabel: String,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    dismissLabel: String? = null,
    onDismiss: (() -> Unit)? = null,
) {
    AlertDialog(
        onDismissRequest = { onDismiss?.invoke() },
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = { TextButton(onClick = onConfirm) { Text(confirmLabel) } },
        dismissButton =
            dismissLabel?.let { { TextButton(onClick = { onDismiss?.invoke() }) { Text(it) } } },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest, modifier = modifier, content = content)
}

@Composable
fun AppSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Snackbar(
        modifier = modifier.defaultMinSize(minHeight = EmberDimens.Snackbar.minHeight),
        action = actionLabel?.let { { TextButton(onClick = { onAction?.invoke() }) { Text(it) } } },
    ) {
        Text(message)
    }
}

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        containerColor = containerColor,
        contentColor = contentColor,
        content = content,
    )
}

@Composable
fun AppLoadingState(modifier: Modifier = Modifier, label: String? = null) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(modifier = Modifier.size(EmberDimens.StateView.mediumSpinnerSize))
        if (label != null) {
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AppEmptyState(
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
    action: (@Composable () -> Unit)? = null,
) {
    AppStateMessage(title = title, message = message, modifier = modifier, action = action)
}

@Composable
fun AppErrorState(
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
    action: (@Composable () -> Unit)? = null,
) {
    AppStateMessage(
        title = title,
        message = message,
        modifier = modifier,
        action = action,
        titleColor = MaterialTheme.colorScheme.error,
    )
}

@Composable
private fun AppStateMessage(
    title: String,
    modifier: Modifier = Modifier,
    message: String? = null,
    action: (@Composable () -> Unit)? = null,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = titleColor,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        if (message != null) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
        if (action != null) {
            Box(modifier = Modifier.defaultMinSize(minHeight = EmberSpacing.lg)) { action() }
        }
    }
}

private fun initialsFor(label: String): String {
    val initials =
        label
            .trim()
            .split(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString(separator = "") { it.first().uppercaseChar().toString() }
    return initials.ifBlank { "?" }
}
