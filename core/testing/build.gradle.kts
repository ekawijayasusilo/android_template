plugins {
    id("template.android.library")
    id("template.test")
}

android { namespace = "com.template.core.testing" }

dependencies {
    api(project(":core:common"))
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(platform(libs.koin.bom))
    api(libs.koin.core)
    api(libs.koin.test)
    api(libs.koin.test.junit4)
    api(libs.ktor.client.mock)
}
