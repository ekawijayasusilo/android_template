package com.template.core.testing.roborazzi

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RoborazziATFAccessibilityCheckOptions
import com.github.takahirom.roborazzi.RoborazziATFAccessibilityChecker
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset

/**
 * Shared roborazzi-accessibility-check (ATF) config (checklist 5.2 / FR-A11Y-3b).
 *
 * The ATF check (missing label · contrast · touch-target ≥ 48dp) is attached to a [RoborazziRule]
 * with an [AccessibilityCheckAfterTestStrategy] in the consuming module's test. Failure level is
 * [RoborazziATFAccessibilityChecker.CheckLevel.Error] so a11y regressions fail the build, not just
 * warn.
 */
@OptIn(ExperimentalRoborazziApi::class)
object RoborazziA11y {

    fun checkOptions(
        level: RoborazziATFAccessibilityChecker.CheckLevel =
            RoborazziATFAccessibilityChecker.CheckLevel.Error
    ): RoborazziATFAccessibilityCheckOptions =
        RoborazziATFAccessibilityCheckOptions(
            checker = RoborazziATFAccessibilityChecker(preset = AccessibilityCheckPreset.LATEST),
            failureLevel = level,
        )
}
