package com.template.core.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.template.core.designsystem.theme.EmberDimens

/**
 * Thin M3 elevated card wrapper (FR-DS-2). Padding + resting elevation come from Ember tokens;
 * callers supply content. An optional [onClick] makes the whole card a single accessible target.
 */
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val elevation =
        CardDefaults.elevatedCardElevation(defaultElevation = EmberDimens.Card.shadowElevation)
    if (onClick != null) {
        ElevatedCard(onClick = onClick, modifier = modifier, elevation = elevation) {
            Column(Modifier.padding(EmberDimens.Card.padding)) { content() }
        }
    } else {
        ElevatedCard(modifier = modifier, elevation = elevation) {
            Column(Modifier.padding(EmberDimens.Card.padding)) { content() }
        }
    }
}

@ThemePreviews
@Composable
internal fun AppCardPreview() {
    EmberPreview {
        AppCard {
            Text("Card title")
            Text("Supporting body text inside an elevated Ember card.")
        }
    }
}
