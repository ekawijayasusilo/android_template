package com.template.core.designsystem

import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.checkRoboAccessibility
import com.template.core.testing.roborazzi.PreviewScanner
import com.template.core.testing.roborazzi.RoborazziA11y
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * ATF accessibility gate (FR-A11Y-3b): runs the roborazzi-accessibility-check (missing label ·
 * contrast · touch-target ≥ 48dp) at failure level Error over every `:core:designsystem` preview. A
 * violation fails the build, not just warns.
 */
@OptIn(ExperimentalRoborazziApi::class)
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class PreviewAccessibilityTest(private val preview: ComposablePreview<AndroidPreviewInfo>) {
    @get:Rule val composeRule = createComposeRule()

    @Test
    fun accessible() {
        composeRule.setContent { preview() }
        // Dialog/popup previews render into a second root window, so onRoot() (which requires
        // exactly
        // one root) is unsafe — check every root instead.
        val roots = composeRule.onAllNodes(isRoot())
        repeat(roots.fetchSemanticsNodes().size) { index ->
            roots[index].checkRoboAccessibility(
                roborazziATFAccessibilityCheckOptions = RoborazziA11y.checkOptions()
            )
        }
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun previews(): List<ComposablePreview<AndroidPreviewInfo>> =
            PreviewScanner.previewsIn("com.template.core.designsystem")
    }
}
