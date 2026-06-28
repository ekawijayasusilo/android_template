package com.template.core.datastore.settings

enum class ThemePreference {
    System,
    Light,
    Dark,
}

data class AppSettings(
    val themePreference: ThemePreference = ThemePreference.System,
    val dynamicColorEnabled: Boolean = false,
    val languageTag: String? = null,
)
