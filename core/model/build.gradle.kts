plugins {
    id("template.android.library")
    id("template.test")
}

android { namespace = "com.template.core.model" }

dependencies { implementation(project(":core:common")) }
