package com.template.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/** Shared Android config applied by both the application and library conventions.
 *  KSP is not compatible with AGP built-in Kotlin in this toolchain, so app/library conventions apply
 *  the Kotlin Android plugin explicitly while keeping AGP 9's new Android DSL surface. */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.compileSdk = 36
    commonExtension.defaultConfig.minSdk = 28
    commonExtension.compileOptions.sourceCompatibility = JavaVersion.VERSION_21
    commonExtension.compileOptions.targetCompatibility = JavaVersion.VERSION_21
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }
}
