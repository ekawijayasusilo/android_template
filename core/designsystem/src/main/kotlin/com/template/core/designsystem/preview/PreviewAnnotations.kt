package com.template.core.designsystem.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light",
    group = "Theme",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    group = "Theme",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
annotation class AppThemePreviews

@Preview(name = "Font scale 1.0", group = "Font scale", fontScale = 1.0f, showBackground = true)
@Preview(name = "Font scale 1.5", group = "Font scale", fontScale = 1.5f, showBackground = true)
annotation class AppFontScalePreviews

@AppThemePreviews @AppFontScalePreviews annotation class AppComponentPreviews
