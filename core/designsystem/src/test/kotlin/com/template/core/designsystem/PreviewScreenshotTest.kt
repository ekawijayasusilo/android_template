package com.template.core.designsystem

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.captureRoboImage
import com.template.core.testing.roborazzi.PreviewScanner
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Auto-generated Roborazzi screenshot test (FR-DS-4): one golden per `@Preview` variant in
 * `:core:designsystem`, discovered by ComposablePreviewScanner. Record with `recordRoborazziDebug`,
 * gate with `verifyRoborazziDebug`.
 */
@OptIn(ExperimentalRoborazziApi::class)
@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class PreviewScreenshotTest(private val preview: ComposablePreview<AndroidPreviewInfo>) {
    @Test
    fun snapshot() {
        preview.captureRoboImage(filePath = PreviewScanner.screenshotPathFor(preview))
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun previews(): List<ComposablePreview<AndroidPreviewInfo>> =
            PreviewScanner.previewsIn("com.template.core.designsystem")
    }
}
