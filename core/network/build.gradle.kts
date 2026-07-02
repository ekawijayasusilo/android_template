plugins {
    id("template.android.library")
    id("template.test")
    id("template.android.koin")
}

android { namespace = "com.template.core.network" }

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(platform(libs.koin.bom))
    api(libs.koin.core)
    api(libs.ktor.client.auth)
    api(libs.ktor.client.content.negotiation)
    api(libs.ktor.client.core)
    api(libs.ktor.client.okhttp)
    api(libs.ktor.serialization.kotlinx.json)
    api(libs.okhttp)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:testing"))
    testImplementation(libs.ktor.client.mock)
}
