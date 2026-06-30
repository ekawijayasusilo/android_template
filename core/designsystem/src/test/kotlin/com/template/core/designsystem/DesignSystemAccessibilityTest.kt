package com.template.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.checkRoboAccessibility
import com.template.core.designsystem.theme.AppTheme
import com.template.core.testing.RoborazziDefaults
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [35], qualifiers = "w400dp-h500dp-xxhdpi")
@OptIn(ExperimentalRoborazziApi::class)
class DesignSystemAccessibilityTest {
    @get:Rule val composeRule = createComposeRule()

    @Test
    fun unlabeledSmallInteractiveTargetFailsAtErrorLevel() {
        composeRule.setContent {
            AppTheme {
                Box(
                    modifier =
                        Modifier.testTag("invalidTarget")
                            .size(24.dp)
                            .background(Color.Red)
                            .clickable(onClick = {})
                )
            }
        }

        val failure =
            runCatching {
                    composeRule
                        .onNodeWithTag("invalidTarget")
                        .checkRoboAccessibility(RoborazziDefaults.accessibilityOptions())
                }
                .exceptionOrNull()

        assertNotNull(
            "Expected roborazzi-accessibility-check to fail for an unlabeled 24dp touch target.",
            failure,
        )
    }
}
