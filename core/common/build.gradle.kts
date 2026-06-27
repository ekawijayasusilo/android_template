plugins {
    id("template.android.library")
    id("template.test")
}

android { namespace = "com.template.core.common" }

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.kotlinx.coroutines.test)
}
