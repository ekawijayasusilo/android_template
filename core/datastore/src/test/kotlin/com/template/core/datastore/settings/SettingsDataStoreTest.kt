package com.template.core.datastore.settings

import com.template.core.datastore.FakePreferenceDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SettingsDataStoreTest {
    @Test
    fun writesAndReadsSettings() = runTest {
        val settingsDataStore = SettingsDataStore(FakePreferenceDataStore())

        settingsDataStore.setThemePreference(ThemePreference.Dark)
        settingsDataStore.setDynamicColorEnabled(true)
        settingsDataStore.setLanguageTag("id-ID")

        assertEquals(
            AppSettings(
                themePreference = ThemePreference.Dark,
                dynamicColorEnabled = true,
                languageTag = "id-ID",
            ),
            settingsDataStore.settings.first(),
        )

        settingsDataStore.setLanguageTag(null)

        assertEquals(null, settingsDataStore.settings.first().languageTag)
    }
}
