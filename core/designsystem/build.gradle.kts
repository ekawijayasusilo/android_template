plugins {
    id("template.android.library")
    id("template.android.compose")
    alias(libs.plugins.roborazzi) // record/verify screenshot tasks
}

// NOTE: Showkase (FR-DS-5) is deferred — it needs KSP, which AGP 9's built-in Kotlin (chosen by the
// frozen PR-1 build-logic, no kotlin.android plugin applied) rejects: "KSP is not compatible with
// Android Gradle Plugin's built-in Kotlin." Enabling KSP requires android.builtInKotlin=false +
// kotlin("android") in build-logic — out of scope for PR-8 (build-logic is frozen). Same gap blocks
// the Room convention (PR-4). ComposablePreviewScanner needs no KSP, so screenshot/a11y coverage is
// unaffected. Wire Showkase once build-logic gains KSP support (tracked for a foundation
// follow-up).

android {
    namespace = "com.template.core.designsystem"
    // Robolectric needs merged Android resources (fonts, theme) for JVM screenshot rendering.
    testOptions { unitTests { isIncludeAndroidResources = true } }
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)
    api(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material.icons.extended)
    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(project(":core:testing"))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.compose.ui.tooling)
    // Provides the ComponentActivity + manifest that createComposeRule() launches under
    // Robolectric.
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
