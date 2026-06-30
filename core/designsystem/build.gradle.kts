import com.github.takahirom.roborazzi.ExperimentalRoborazziApi

plugins {
    id("template.android.library")
    id("template.android.compose")
    id("template.test")
    alias(libs.plugins.ksp)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.template.core.designsystem"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            all { it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware" }
        }
    }
}

@OptIn(ExperimentalRoborazziApi::class)
roborazzi {
    outputDir.set(file("src/test/screenshots"))
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf("com.template.core.designsystem")
        includePrivatePreviews = false
        generatedTestClassCount = 1
        robolectricConfig = mapOf("sdk" to "[35]", "qualifiers" to "\"w400dp-h500dp-xxhdpi\"")
    }
}

dependencies {
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.showkase.annotation)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.showkase)

    kspDebug(libs.showkase.processor)

    testImplementation(project(":core:testing"))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    testImplementation(libs.androidx.compose.ui.test.junit4.accessibility)
    testImplementation(libs.robolectric)
    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.junit.rule)
    testImplementation(libs.roborazzi.compose.preview.scanner.support)
    testImplementation(libs.composable.preview.scanner.android)
    testImplementation(libs.roborazzi.accessibility.check)
}


