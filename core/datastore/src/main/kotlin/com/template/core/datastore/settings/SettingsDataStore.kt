package com.template.core.datastore.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SettingsDataStore(private val dataStore: DataStore<Preferences>) {
    val settings: Flow<AppSettings> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences()) else throw exception
            }
            .map { preferences ->
                AppSettings(
                    themePreference =
                        preferences[Keys.themePreference]?.toThemePreference()
                            ?: ThemePreference.System,
                    dynamicColorEnabled = preferences[Keys.dynamicColorEnabled] ?: false,
                    languageTag = preferences[Keys.languageTag],
                )
            }

    suspend fun setThemePreference(themePreference: ThemePreference) {
        dataStore.edit { preferences -> preferences[Keys.themePreference] = themePreference.name }
    }

    suspend fun setDynamicColorEnabled(enabled: Boolean) {
        dataStore.edit { preferences -> preferences[Keys.dynamicColorEnabled] = enabled }
    }

    suspend fun setLanguageTag(languageTag: String?) {
        dataStore.edit { preferences ->
            if (languageTag == null) {
                preferences.remove(Keys.languageTag)
            } else {
                preferences[Keys.languageTag] = languageTag
            }
        }
    }

    private fun String.toThemePreference(): ThemePreference =
        ThemePreference.entries.firstOrNull { it.name == this } ?: ThemePreference.System

    private object Keys {
        val themePreference = stringPreferencesKey("theme_preference")
        val dynamicColorEnabled = booleanPreferencesKey("dynamic_color_enabled")
        val languageTag = stringPreferencesKey("language_tag")
    }
}
