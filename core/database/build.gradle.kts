plugins {
    id("template.android.library")
    id("template.android.room")
    id("template.test")
}

android { namespace = "com.template.core.database" }

dependencies {
    api(project(":core:common"))
    api(project(":core:model"))
    api(libs.room.ktx)
    api(libs.room.runtime)

    testImplementation(libs.room.testing)
}
