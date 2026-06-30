package com.template.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

/** Shared Android config applied by both the application and library conventions.
 *  AGP 9 new DSL: configure through [CommonExtension] (non-generic) via direct property access — the
 *  lambda-accepting overloads were removed. KSP-backed processors require android.builtInKotlin=false, so convention plugins apply the Kotlin Android plugin and derive the Kotlin jvmTarget from compileOptions (JDK 21). */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.compileSdk = 36
    commonExtension.defaultConfig.minSdk = 28
    commonExtension.compileOptions.sourceCompatibility = JavaVersion.VERSION_21
    commonExtension.compileOptions.targetCompatibility = JavaVersion.VERSION_21
}

