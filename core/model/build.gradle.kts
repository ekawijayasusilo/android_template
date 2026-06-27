plugins {
    id("template.android.library")
    id("template.test")
}

android { namespace = "com.template.core.model" }

dependencies { api(project(":core:common")) }
