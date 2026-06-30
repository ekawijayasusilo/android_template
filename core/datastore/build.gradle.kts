plugins {
    id("template.android.library")
    id("template.test")
    alias(libs.plugins.kotlin.serialization)
}

android { namespace = "com.template.core.datastore" }

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(libs.androidx.datastore.core)
    api(libs.androidx.datastore.preferences)
    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.serialization.protobuf)

    testImplementation(libs.kotlinx.coroutines.test)
}
