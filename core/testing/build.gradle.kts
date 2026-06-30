plugins {
    id("template.android.library")
    id("template.android.compose")
    id("template.test")
}

android {
    namespace = "com.template.core.testing"

    testOptions {
        unitTests { isIncludeAndroidResources = true }
    }
}

dependencies {
    api(libs.junit)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.test.junit4)
    api(libs.androidx.compose.ui.test.junit4.accessibility)
    api(libs.roborazzi)
    api(libs.roborazzi.compose)
    api(libs.roborazzi.junit.rule)
    api(libs.roborazzi.accessibility.check)
    api(libs.robolectric)

    implementation(libs.androidx.compose.material3)
}
