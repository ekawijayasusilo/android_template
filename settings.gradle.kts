pluginManagement {
    // build-logic convention plugins (template.android.*, template.detekt/kover/test) — frozen by PR-1.
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android_template"

// Module includes are append-only — each later PR (PR-2..PR-13) appends its module(s) below.
include(":app")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":core:testing")
