plugins {
    id("template.android.library")
    id("template.android.compose")
}

android { namespace = "com.template.core.testing" }

// PR-8 slice-2: Roborazzi screenshot + ATF accessibility substrate + ComposablePreviewScanner
// wiring.
// Slice-1 (coroutine/Koin/MockEngine rules, checklist 5.1) arrives with PR-3 and appends below.
dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.junit)
    api(libs.robolectric)
    api(libs.roborazzi)
    api(libs.roborazzi.compose)
    api(libs.roborazzi.junit.rule)
    api(libs.roborazzi.accessibility.check)
    api(libs.roborazzi.preview.scanner.support)
    api(libs.composable.preview.scanner)
    api(libs.androidx.compose.ui.test.junit4)
    api(libs.androidx.compose.ui.tooling)
}
