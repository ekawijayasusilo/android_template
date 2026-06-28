package com.template.core.testing.roborazzi

import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

/**
 * Shared ComposablePreviewScanner wiring (checklist 5.2 / FR-DS-4).
 *
 * Each consuming module (e.g. :core:designsystem) supplies its own package root; the scanner
 * expands every `@Preview` — including multipreview (`@PreviewLightDark`, `@PreviewFontScales`) and
 * `@PreviewParameter` provider values — into one [ComposablePreview] per variant, so a
 * parameterized Robolectric test yields exactly one screenshot assertion per preview.
 */
object PreviewScanner {

    /** Default Roborazzi golden output directory, relative to the module dir. */
    const val OUTPUT_DIR: String = "src/test/screenshots"

    /**
     * Scan [packageTrees] for all Compose previews. Cache the result in a `by lazy` companion val.
     */
    fun previewsIn(vararg packageTrees: String): List<ComposablePreview<AndroidPreviewInfo>> =
        AndroidComposablePreviewScanner().scanPackageTrees(*packageTrees).getPreviews()

    /**
     * Stable, collision-free golden path for [preview] (method name + config: ui mode, font scale).
     */
    fun screenshotPathFor(preview: ComposablePreview<AndroidPreviewInfo>): String =
        "$OUTPUT_DIR/${AndroidPreviewScreenshotIdBuilder(preview).build()}.png"
}
