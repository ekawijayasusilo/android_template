package com.template.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/** Enables Compose, wires the Compose BOM, and registers the slackhq compose-lints pack via
 *  lintChecks(...) (PRD §8.14 GATE-1). Applied on top of an already-configured android extension. */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension,
) {
    commonExtension.buildFeatures.compose = true

    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))
        add("lintChecks", libs.findLibrary("compose-lint-checks").get())
    }
}
