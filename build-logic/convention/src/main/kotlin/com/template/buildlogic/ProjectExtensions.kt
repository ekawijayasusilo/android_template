package com.template.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/** Type-unsafe catalog accessor — lets convention plugins resolve libraries/versions by alias at
 *  apply time, so build-logic compiles even before later PRs append a library's coordinate. */
internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
