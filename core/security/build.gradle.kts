plugins {
    id("template.android.library")
    id("template.test")
}

android { namespace = "com.template.core.security" }

dependencies { api(project(":core:common")) }
