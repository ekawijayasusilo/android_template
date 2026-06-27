package com.template.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

/** Shared Android config applied by both the application and library conventions.
 *  AGP 9 new DSL: configure through [CommonExtension] (non-generic) via direct property access — the
 *  lambda-accepting overloads were removed. AGP 9 has built-in Kotlin, so the Kotlin jvmTarget is
 *  derived from compileOptions (JDK 21) and the kotlin.android plugin must NOT be applied. */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.compileSdk = 36
    commonExtension.defaultConfig.minSdk = 28
    commonExtension.compileOptions.sourceCompatibility = JavaVersion.VERSION_21
    commonExtension.compileOptions.targetCompatibility = JavaVersion.VERSION_21
}
