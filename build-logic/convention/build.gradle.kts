plugins {
    `kotlin-dsl`
}

group = "com.template.buildlogic"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.spotless.gradlePlugin)
    compileOnly(libs.kover.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "template.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "template.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "template.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "template.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidKoin") {
            id = "template.android.koin"
            implementationClass = "KoinConventionPlugin"
        }
        register("androidRoom") {
            id = "template.android.room"
            implementationClass = "RoomConventionPlugin"
        }
        register("templateTest") {
            id = "template.test"
            implementationClass = "TestConventionPlugin"
        }
        register("templateDetekt") {
            id = "template.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("templateKover") {
            id = "template.kover"
            implementationClass = "KoverConventionPlugin"
        }
    }
}
