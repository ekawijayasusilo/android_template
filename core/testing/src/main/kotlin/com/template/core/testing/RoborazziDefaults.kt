package com.template.core.testing

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.AccessibilityCheckAfterTestStrategy
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziATFAccessibilityCheckOptions
import com.github.takahirom.roborazzi.RoborazziATFAccessibilityChecker
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.RoborazziRule

@OptIn(ExperimentalRoborazziApi::class)
object RoborazziDefaults {
    const val screenshotOutputDirectory = "src/test/screenshots"

    fun accessibilityOptions(
        failureLevel: RoborazziATFAccessibilityChecker.CheckLevel =
            RoborazziATFAccessibilityChecker.CheckLevel.Error
    ): RoborazziATFAccessibilityCheckOptions =
        RoborazziATFAccessibilityCheckOptions(
            checker = RoborazziATFAccessibilityChecker(),
            failureLevel = failureLevel,
        )

    fun accessibilityCheckStrategy(): AccessibilityCheckAfterTestStrategy =
        AccessibilityCheckAfterTestStrategy {
            accessibilityOptions()
        }

    fun ruleOptions(
        outputDirectoryPath: String = screenshotOutputDirectory,
        roborazziOptions: RoborazziOptions = RoborazziOptions(),
    ): RoborazziRule.Options =
        RoborazziRule.Options()
            .copy(
                captureType = RoborazziRule.CaptureType.LastImage(),
                outputDirectoryPath = outputDirectoryPath,
                roborazziOptions = roborazziOptions,
                roborazziAccessibilityOptions = accessibilityOptions(),
                accessibilityCheckStrategy = accessibilityCheckStrategy(),
            )
}

@OptIn(ExperimentalRoborazziApi::class)
fun ComposeTestRule.createRoborazziRule(
    outputDirectoryPath: String = RoborazziDefaults.screenshotOutputDirectory
): RoborazziRule =
    RoborazziRule(
        this,
        onRoot(),
        RoborazziDefaults.ruleOptions(outputDirectoryPath = outputDirectoryPath),
    )
